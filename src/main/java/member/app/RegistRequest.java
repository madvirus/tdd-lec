package member.app;

import org.hibernate.validator.constraints.NotEmpty;

public class RegistRequest {
	@NotEmpty
	private String id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String password;
	@NotEmpty
	private String passwordConfirm;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public boolean matchPasswordAndConfirm() {
		return password != null && password.equals(passwordConfirm);
	}

}
