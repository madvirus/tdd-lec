package member.ui;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class MemberControllerTest {
	private MockMvc mockMvc;
	private MemberController memberController = new MemberController();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
	}

	@Test
	public void regist_Form() throws Exception {
		mockMvc.perform(get("/member/reg"))
				.andExpect(status().isOk())
				.andExpect(view().name("member/regForm"));
	}
}
