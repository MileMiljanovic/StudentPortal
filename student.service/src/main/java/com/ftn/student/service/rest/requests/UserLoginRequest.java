package com.ftn.student.service.rest.requests;

import javax.validation.constraints.NotNull;

public class UserLoginRequest {
	
	@NotNull(message="Nedostaje formular id")
	private String username;
	
	@NotNull(message="Nedostaje odgovor")
	private String password;

}
