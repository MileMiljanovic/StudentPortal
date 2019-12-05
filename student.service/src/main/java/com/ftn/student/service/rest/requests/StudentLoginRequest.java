package com.ftn.student.service.rest.requests;

import javax.validation.constraints.NotNull;

public class StudentLoginRequest {
	
	@NotNull(message="Unesite broj indeksa")
	private String brIndeksa;

	public String getBrIndeksa() {
		return brIndeksa;
	}

	public void setBrIndeksa(String brIndeksa) {
		this.brIndeksa = brIndeksa;
	}
	
}
