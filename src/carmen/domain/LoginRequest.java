package carmen.domain;

public class LoginRequest {
	
	private String uid;
	private String upwd;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public LoginRequest() {}
	public LoginRequest(String uid, String upwd) {
		super();
		this.uid = uid;
		this.upwd = upwd;
	}
	
	
}
