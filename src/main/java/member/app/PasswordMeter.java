package member.app;

import org.springframework.stereotype.Component;

public class PasswordMeter {

	public PasswordLevel meter(String password) {
		return PasswordLevel.NORMAL;
	}

}
