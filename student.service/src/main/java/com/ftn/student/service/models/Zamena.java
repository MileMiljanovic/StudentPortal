package com.ftn.student.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "zamene")
@Table(name = "zamene")
public class Zamena {
	
	@Id
	@Column(name = "idzamena")
	private String idzamena;

	@Column(name="idformulara")
	private String formular;
	
	@Column(name="domaci")
	private String predmetDomaci;
	
	@Column(name="domaciprogram")
	private String studijskiProgramDomaci;
	
	@Column(name="strani")
	private String predmetStrani;
	
	@Column(name="straniprogram")
	private String studijskiProgramStrani;
	
	@Column(name = "odobreno")
	private String odobreno;

	public Zamena() {}

	public Zamena(String idzamena, String formular, String predmetDomaci, String studijskiProgramDomaci,
			String predmetStrani, String studijskiProgramStrani, String odobreno) {
		super();
		this.idzamena = idzamena;
		this.formular = formular;
		this.predmetDomaci = predmetDomaci;
		this.studijskiProgramDomaci = studijskiProgramDomaci;
		this.predmetStrani = predmetStrani;
		this.studijskiProgramStrani = studijskiProgramStrani;
		this.odobreno = odobreno;
	}

	public String getIdzamena() {
		return idzamena;
	}

	public void setIdzamena(String idzamena) {
		this.idzamena = idzamena;
	}

	public String getFormular() {
		return formular;
	}

	public void setFormular(String formular) {
		this.formular = formular;
	}

	public String getPredmetDomaci() {
		return predmetDomaci;
	}

	public void setPredmetDomaci(String predmetDomaci) {
		this.predmetDomaci = predmetDomaci;
	}

	public String getStudijskiProgramDomaci() {
		return studijskiProgramDomaci;
	}

	public void setStudijskiProgramDomaci(String studijskiProgramDomaci) {
		this.studijskiProgramDomaci = studijskiProgramDomaci;
	}

	public String getPredmetStrani() {
		return predmetStrani;
	}

	public void setPredmetStrani(String predmetStrani) {
		this.predmetStrani = predmetStrani;
	}

	public String getStudijskiProgramStrani() {
		return studijskiProgramStrani;
	}

	public void setStudijskiProgramStrani(String studijskiProgramStrani) {
		this.studijskiProgramStrani = studijskiProgramStrani;
	}

	public String getOdobreno() {
		return odobreno;
	}

	public void setOdobreno(String odobreno) {
		this.odobreno = odobreno;
	}
	

}
