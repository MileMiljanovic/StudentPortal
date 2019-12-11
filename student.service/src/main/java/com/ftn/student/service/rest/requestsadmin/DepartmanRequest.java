package com.ftn.student.service.rest.requestsadmin;

import javax.validation.constraints.NotNull;

import com.ftn.student.service.models.Departman;

public class DepartmanRequest {
	
	@NotNull(message="Nedostaje departman")
	private Departman departman;

	public Departman getDepartman() {
		return departman;
	}

	public void setDepartman(Departman departman) {
		this.departman = departman;
	}
	

}
