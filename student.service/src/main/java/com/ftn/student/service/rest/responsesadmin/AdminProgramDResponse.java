package com.ftn.student.service.rest.responsesadmin;

import java.util.List;

import com.ftn.student.service.models.Korisnik;
import com.ftn.student.service.models.StudijskiProgramDomaci;

public class AdminProgramDResponse {
	
	private List<StudijskiProgramDomaci> programi;
	private List<Korisnik> sefovi;
	
	public AdminProgramDResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AdminProgramDResponse(List<StudijskiProgramDomaci> programi, List<Korisnik> sefovi) {
		super();
		this.programi = programi;
		this.sefovi = sefovi;
	}

	public List<StudijskiProgramDomaci> getProgrami() {
		return programi;
	}

	public void setProgrami(List<StudijskiProgramDomaci> programi) {
		this.programi = programi;
	}

	public List<Korisnik> getSefovi() {
		return sefovi;
	}

	public void setSefovi(List<Korisnik> sefovi) {
		this.sefovi = sefovi;
	}

}
