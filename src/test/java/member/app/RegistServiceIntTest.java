package member.app;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import config.AppConfiguration;
import member.dao.MemberDao;
import member.model.Member;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class })
@Sql({ "classpath:/preload_member.sql" })
public class RegistServiceIntTest {

	@Autowired
	private RegistService registService;

	@Autowired
	private MemberDao memberDao;

	@Transactional
	@Test
	public void regist_Dup_Id() throws Exception {
		RegistRequest registRequest = new RegistRequest();
		registRequest.setId("madvirus");
		registRequest.setName("TDD");
		registRequest.setPassword("142fwgt4451!");
		registRequest.setPasswordConfirm("142fwgt4451!");

		try {
			registService.regist(registRequest);
			fail();
		} catch (DuplicateIdException ex) {
		}
	}

	@Transactional
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
}
