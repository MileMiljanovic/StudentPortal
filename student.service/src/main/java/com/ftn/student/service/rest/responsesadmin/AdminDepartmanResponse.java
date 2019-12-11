package com.ftn.student.service.rest.responsesadmin;

import java.util.List;

import com.ftn.student.service.models.Departman;
import com.ftn.student.service.models.Korisnik;

public class AdminDepartmanResponse {
	
	private List<Departman> departmani;
	private List<Korisnik> koordinatori;
	
	public AdminDepartmanResponse() {}

	public AdminDepartmanResponse(List<Departman> departmani, List<Korisnik> koordinatori) {
		super();
		this.departmani = departmani;
		this.koordinatori = koordinatori;
	}

	public List<Departman> getDepartmani() {
		return departmani;
	}

	public void setDepartmani(List<Departman> departmani) {
		this.departmani = departmani;
	}

	public List<Korisnik> getKoordinatori() {
		return koordinatori;
	}

	public void setKoordinatori(List<Korisnik> koordinatori) {
		this.koordinatori = koordinatori;
	}

}
