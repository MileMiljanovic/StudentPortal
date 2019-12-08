package com.ftn.student.service.rest.requests;

import javax.validation.constraints.NotNull;

public class UserLoginRequest {
	
	@NotNull(message="Nedostaje korisnicko ime")
	private String username;
	
	@NotNull(message="Nedostaje lozinka")
	private String password;

}
