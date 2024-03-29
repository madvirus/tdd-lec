package member.app;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import member.dao.MemoryMemberDao;
import member.model.Member;

public class RegistServiceTest {

	private RegistService registService;
	private PasswordMeter passwordMeter;
	private MemoryMemberDao memberDao;

	@Before
	public void setup() {
		passwordMeter = new PasswordMeter();
		memberDao = new MemoryMemberDao();
		registService = new RegistService();
		registService.setPasswordMeter(passwordMeter);
		registService.setMemberDao(memberDao);
	}

	@Test
	public void idIsDup_Then_DupIdEx_Should_Be_Thrown() {
		// given: id가 dupid인 Member 존재
		memberDao.insert(new Member("dupid", "n", "p"));
		
		// when: dupid로 회원 가입 신청하면
		RegistRequest registRequest = new RegistRequest();
		registRequest.setId("dupid");
		registRequest.setName("name");
		registRequest.setPassword("strongPW1@");
		try {
			registService.regist(registRequest);
			fail();
		} catch (DuplicateIdException ex) {
			// then: 중복 ID 익셉션 발생해야 함
		}
	}	
	@Test
	public void passwordIsWeak_Then_WeakPwEx_Should_Be_Thrown() {
		RegistRequest registRequest = new RegistRequest();
		registRequest.setId("id");
		registRequest.setName("name");
		registRequest.setPassword("1234");
		try {
			registService.regist(registRequest);
			fail();
		} catch (WeakPasswordException ex) {
		}
	}


	@Test
	public void allPass_Then_Regist_Success() throws Exception {
		RegistRequest regReq = new RegistRequest();
		regReq.setId("nodupid");
		regReq.setName("name");
		regReq.setPassword("strongPW1@");
		registService.regist(regReq);
		
		// then: 신규 회원 존재함
		Member member = memberDao.selectById("nodupid");
		assertThat(member, notNullValue());
		assertThat(member.getName(), equalTo(regReq.getName()));
	}
	

}
