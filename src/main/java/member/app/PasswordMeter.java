package member.app;

import org.springframework.stereotype.Component;

public class PasswordMeter {

	public PasswordLevel meter(String password) {
		if ("1234".equals(password)) 
			return PasswordLevel.WEAK;
		return PasswordLevel.NORMAL;
	}

}
