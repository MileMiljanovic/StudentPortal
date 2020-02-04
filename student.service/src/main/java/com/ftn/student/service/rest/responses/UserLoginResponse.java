package com.ftn.student.service.rest.responses;

import java.util.List;

import com.ftn.student.service.models.Formular;

public class UserLoginResponse {
	
	private List<Formular> formulari;
	
	public UserLoginResponse() {
		super();
	}

	public UserLoginResponse(List<Formular> formulari) {
		super();
		this.formulari = formulari;
	}

	public List<Formular> getFormulari() {
		return formulari;
	}

	public void setFormulari(List<Formular> formulari) {
		this.formulari = formulari;
	}
	
	

}
