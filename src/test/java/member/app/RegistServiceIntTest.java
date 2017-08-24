package member.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import config.AppConfiguration;
import member.dao.MemberDao;
import member.model.Member;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class })
public class RegistServiceIntTest {

	@Autowired
	private RegistService registService;

	@Autowired
	private MemberDao memberDao;

	@Transactional
	@Test
	public void regist() throws Exception {
		RegistRequest registRequest = new RegistRequest();
		registService.regist(registRequest);

		Member mem = memberDao.selectById(registRequest.getId());
	}
}
