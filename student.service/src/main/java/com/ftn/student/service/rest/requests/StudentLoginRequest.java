package com.ftn.student.service.rest.requests;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class StudentLoginRequest {
	
	@Id
	@NotEmpty(message = "Unesite broj indeksa")
	private String brIndeksa;

	public String getBrIndeksa() {
		return brIndeksa;
	}

	public void setBrIndeksa(String brIndeksa) {
		this.brIndeksa = brIndeksa;
	}
	
	

}
