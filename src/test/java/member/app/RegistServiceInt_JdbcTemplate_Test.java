package member.app;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import config.AppConfiguration;
import member.dao.MemberDao;
import member.model.Member;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class })
public class RegistServiceInt_JdbcTemplate_Test {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@Transactional
	@Test
	public void regist_Dup_Id() throws Exception {
		jdbcTemplate.update("insert into member (member_id, name, password) values (?,?,?)", 
				"madvirus", "최범균", "1234");

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

	@Autowired
	private RegistService registService;
	@Autowired
	private MemberDao memberDao;
}
