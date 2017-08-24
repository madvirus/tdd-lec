package member.app;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import member.dao.MemberDao;
import member.model.Member;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:app-conf.xml" })
public class RegistServiceIntTest_XmlConf {

	@Autowired
	private RegistService registService;

	@Autowired
	private MemberDao memberDao;

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
