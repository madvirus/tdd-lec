package member.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.dao.MemberDao;
import member.model.Member;

@Service
public class RegistService {
	private MemberDao memberDao;

	public void regist(RegistRequest registRequest) {
		checkDuplicateId(registRequest.getId());
		Member member = new Member(registRequest.getId(), 
				registRequest.getName(), registRequest.getPassword());
		memberDao.insert(member);
	}

	private void checkDuplicateId(String id) {
		Member member = memberDao.selectById(id);
		if (member != null)
			throw new DuplicateIdException();
	}

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

}
