package member.app;

import org.springframework.stereotype.Service;

import member.dao.MemberDao;
import member.model.Member;

@Service
public class RegistService {
	private PasswordMeter passwordMeter;
	private MemberDao memberDao;
	
	public void regist(RegistRequest regReq) {
		PasswordLevel pwLvl = passwordMeter.meter(regReq.getPassword());
		if (pwLvl == PasswordLevel.WEAK)
			throw new WeakPasswordException();
		
		Member member = memberDao.selectById(regReq.getId());
		if (member != null)
			throw new DuplicateIdException();
		
		Member newMem = new Member(regReq.getId(),
				regReq.getName(),
				regReq.getPassword());
				
		memberDao.insert(newMem);
	}
	
	public void setPasswordMeter(PasswordMeter passwordMeter) {
		this.passwordMeter = passwordMeter;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
}
