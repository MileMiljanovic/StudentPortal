package com.ftn.student.service.rest.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ftn.student.service.models.Formular;

public class ConfirmationRequest {
	
	@NotNull(message="Nedostaje formular id")
	private Formular formularId;
	
	@NotNull(message="Nedostaje odgovor")
	@Size(min = 1, max = 1)
	private String odgovor;

	public Formular getFormularId() {
		return formularId;
	}

	public void setFormularId(Formular formularId) {
		this.formularId = formularId;
	}

	public String getOdgovor() {
		return odgovor;
	}

	public void setOdgovor(String odgovor) {
		this.odgovor = odgovor;
	}
	
	

}
