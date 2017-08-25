package member.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import member.dao.MemberDao;

public class RegistServiceTest {

	private RegistService registService;
	private MemberDao memberDao;

	@Before
	public void setup() {
		memberDao = ?????; // 실제 구현 객체 사용 불가 
		registService = new RegistService();
		registService.setMemberDao(memberDao);
	}

	@Test
	public void allPass_Then_Regist_Success() throws Exception {
		RegistRequest registRequest = new RegistRequest();
		registRequest.setId("nodupid");
		registRequest.setName("name");
		registRequest.setPassword("strongPW1@");
		registService.regist(registRequest);
		
		// 새로 가입한 회원이 존재함을 검증해야함!!!!!
		// 어떻게 검증?????
	}
	
	@Test
	public void passwordIsWeak_Then_WeakPwEx_Should_Be_Thrown() throws Exception {
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
	public void idIsDup_Then_DupIdEx_Should_Be_Thrown() {
		RegistRequest registRequest = new RegistRequest();
		registRequest.setId("dupid");
		registRequest.setName("name");
		registRequest.setPassword("strongPW1@");
		try {
			registService.regist(registRequest);
			fail();
		} catch (DuplicateIdException ex) {
		}
	}

}
