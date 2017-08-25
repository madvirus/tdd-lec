package member.ui;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import member.app.DuplicateIdException;
import member.app.RegistRequest;
import member.app.RegistService;

@Controller
public class MemberController {
	private RegistService registService;

	@ModelAttribute
	public RegistRequest formData() {
		return new RegistRequest();
	}

	@GetMapping("/member/reg")
	public String form() {
		return "member/regForm";
	}

	@PostMapping("/member/reg")
	public String submit(@Valid RegistRequest req, Errors errors) {
		if (errors.hasErrors()) {
			return "member/regForm";
		}
		if (!req.matchPasswordAndConfirm()) {
			errors.rejectValue("passwordConfirm", "NoMatch");
			return "member/regForm";
		}
		try {
			registService.regist(req);
			return "member/regDone";
		} catch (DuplicateIdException ex) {
			errors.rejectValue("id", "DupId");
			return "member/regForm";
		}
	}

	@Autowired
	public void setRegistService(RegistService registService) {
		this.registService = registService;
	}

}
