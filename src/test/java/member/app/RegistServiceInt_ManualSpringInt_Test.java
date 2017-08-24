package member.app;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import config.AppConfiguration;
import member.dao.MemberDao;
import member.model.Member;

public class RegistServiceInt_ManualSpringInt_Test {

	private GenericApplicationContext ctx;
	private RegistService registService;
	private MemberDao memberDao;
	
	@Before
	public void setup() {
		ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
		registService = ctx.getBean(RegistService.class);
		memberDao = ctx.getBean(MemberDao.class);
	}
	
	@Test
	public void regist() throws Exception {
		RegistRequest registRequest = new RegistRequest();
		registRequest.setId("tdd");
		registRequest.setName("TDD");
		registRequest.setPassword("142fwgt4451!");
		registRequest.setPasswordConfirm("142fwgt4451!");
		
		registService.regist(registRequest);

		Member mem = memberDao.selectById(registRequest.getId());
		assertThat(mem, notNullValue());
	}

	@After
	public void close() {
		ctx.close();
	}

}
