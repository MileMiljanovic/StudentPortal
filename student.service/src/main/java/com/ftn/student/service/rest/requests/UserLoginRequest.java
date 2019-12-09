package com.ftn.student.service.rest.requests;

import javax.validation.constraints.NotNull;

public class UserLoginRequest {
	
	@NotNull(message="Nedostaje korisnicko ime")
	private String username;
	
	@NotNull(message="Nedostaje lozinka")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
