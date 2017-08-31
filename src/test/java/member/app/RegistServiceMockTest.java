package member.app;

import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import member.dao.MemberDao;
import member.model.Member;

public class RegistServiceMockTest {

	private RegistService registService = new RegistService();
	private PasswordMeter passwordMeter = mock(PasswordMeter.class);
	private MemberDao memberDao = mock(MemberDao.class);

	@Before
	public void setup() {
		registService.setPasswordMeter(passwordMeter);
		registService.setMemberDao(memberDao);
		given(passwordMeter.meter(Mockito.anyString())).willReturn(PasswordLevel.NORMAL);
	}

	@Test
	public void idIsDup_Then_DupIdEx_Should_Be_Thrown() {
		// given: id가 dupid인 Member 존재
		given(memberDao.selectById("dupid")).willReturn(new Member("dupid", "n", "p"));

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
			then(memberDao).should(never()).insert(Mockito.any());
		}
	}

	@Test
	public void passwordIsWeak_Then_WeakPwEx_Should_Be_Thrown() {
		given(passwordMeter.meter("1234")).willReturn(PasswordLevel.WEAK);
		
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

		// then: 신규 회원 등록함
		then(memberDao).should().insert(Mockito.any());
	}

}
