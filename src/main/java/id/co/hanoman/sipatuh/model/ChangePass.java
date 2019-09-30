package id.co.hanoman.sipatuh.model;

import javax.validation.constraints.NotNull;

public class ChangePass {
	@NotNull
	private
	String nama;

	@NotNull
	private
	String password;
	
	@NotNull
	private
	String password_baru;

	@NotNull
	private
	String password_baru_check;

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword_baru() {
		return password_baru;
	}

	public void setPassword_baru(String password_baru) {
		this.password_baru = password_baru;
	}

	public String getPassword_baru_check() {
		return password_baru_check;
	}

	public void setPassword_baru_check(String password_baru_check) {
		this.password_baru_check = password_baru_check;
	}


	
}
