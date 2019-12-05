package com.ftn.student.service.rest.requests;

import javax.validation.constraints.NotNull;

public class CancelFormRequest {
	
	@NotNull(message="Nedostaje formular id")
	private String formularId;

	public String getFormularId() {
		return formularId;
	}

	public void setFormularId(String formularId) {
		this.formularId = formularId;
	}
	
	

}
