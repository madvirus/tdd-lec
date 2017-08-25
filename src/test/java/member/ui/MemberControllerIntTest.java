package member.ui;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import config.AppConfiguration;
import config.WebMvcConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { WebMvcConfiguration.class, AppConfiguration.class })
@WebAppConfiguration
public class MemberControllerIntTest {
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Transactional
	@Test
	public void regist_Form() throws Exception {
		mockMvc.perform(get("/member/reg"))
				.andExpect(status().isOk())
				.andExpect(view().name("member/regForm"));
	}

	@Transactional
	@Test
	public void regist_Success() throws Exception {
		mockMvc.perform(post("/member/reg")
				.param("id", "bkchoi").param("name", "최범균")
				.param("password", "1234").param("passwordConfirm", "1234"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(view().name("member/regDone"))
				.andExpect(model().attributeHasNoErrors("registRequest"));
	}

	@Sql({ "classpath:/preload_member.sql" })
	@Transactional
	@Test
	public void regist_Dup_Id() throws Exception {
		mockMvc.perform(post("/member/reg")
				.param("id", "madvirus").param("name", "최범균")
				.param("password", "1234").param("passwordConfirm", "1234"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(view().name("member/regForm"))
				.andExpect(model().attributeHasFieldErrorCode("registRequest", "id", "DupId"));
	}

}
