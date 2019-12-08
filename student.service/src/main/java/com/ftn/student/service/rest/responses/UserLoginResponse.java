package com.ftn.student.service.rest.responses;

import java.util.List;

import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Korisnik;


public class UserLoginResponse {
	
	private Korisnik korisnik;
	private List<Formular> formulari;
	
	public UserLoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserLoginResponse(Korisnik korisnik, List<Formular> formulari) {
		super();
		this.korisnik = korisnik;
		this.formulari = formulari;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<Formular> getFormulari() {
		return formulari;
	}

	public void setFormulari(List<Formular> formulari) {
		this.formulari = formulari;
	}
	
	

}
