package member.model;

public class Member {
	private String memberId;
	private String name;
	private String password;

	public Member() {
	}

	public Member(String memberId, String name, String password) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.password = password;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}

}
