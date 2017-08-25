package member.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.dao.MemberDao;
import member.model.Member;

@Service
public class RegistService {
	private PasswordMeter passwordMeter = new PasswordMeter();
	private MemberDao memberDao;
	
	public void regist(RegistRequest registRequest) {
		if (passwordMeter.meter(registRequest.getPassword()) == PasswordLevel.WEAK)
			throw new WeakPasswordException();
		
		Member member = memberDao.selectById(registRequest.getId());
		if (member != null)
			throw new DuplicateIdException();
		
		memberDao.insert(member);
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	/*
	private MemberDao memberDao;
	private PasswordMeter passwordMeter;

	public void regist(RegistRequest registRequest) {
		PasswordLevel level = passwordMeter.meter(registRequest.getPassword());
		if (level == PasswordLevel.WEAK) throw new WeakPasswordException();
		
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

	@Autowired
	public void setPasswordMeter(PasswordMeter passwordMeter) {
		this.passwordMeter = passwordMeter;
	}
	*/
}
