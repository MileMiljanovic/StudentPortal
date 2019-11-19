package com.ftn.student.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "studijski_programi_strani")
public class StudijskiProgramStrani {
	
	@Id
	@Column(name = "naziv")
	private String naziv;

	public StudijskiProgramStrani() {}

	public StudijskiProgramStrani(String naziv) {
		super();
		this.naziv = naziv;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

}
