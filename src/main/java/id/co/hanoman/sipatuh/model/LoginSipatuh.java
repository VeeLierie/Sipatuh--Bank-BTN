package id.co.hanoman.sipatuh.model;

import javax.validation.constraints.NotNull;

public class LoginSipatuh {
	@NotNull
	String userid ;
	@NotNull
	String password;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
