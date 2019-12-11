package com.ftn.student.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "zamene")
@Table(name = "zamene")
public class Zamena {
	
	@Id
	@Column(name = "idzamena")
	private String idzamena;

	@Column(name="idformulara", nullable=false)
	private String formular;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="domaci", nullable=false)
	private PredmetDomaci predmetDomaci;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="strani", nullable=false)
	private PredmetStrani predmetStrani;
	
	@Column(name = "odobreno")
	private String odobreno;
	
	@Column(name="token", nullable=false)
	@JsonIgnore
	private String token;

	public Zamena() {}

	public Zamena(String idzamena, String formular, PredmetDomaci predmetDomaci, PredmetStrani predmetStrani,
			String odobreno, String token) {
		super();
		this.idzamena = idzamena;
		this.formular = formular;
		this.predmetDomaci = predmetDomaci;
		this.predmetStrani = predmetStrani;
		this.odobreno = odobreno;
		this.token = token;
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

	public PredmetDomaci getPredmetDomaci() {
		return predmetDomaci;
	}

	public void setPredmetDomaci(PredmetDomaci predmetDomaci) {
		this.predmetDomaci = predmetDomaci;
	}

	public PredmetStrani getPredmetStrani() {
		return predmetStrani;
	}

	public void setPredmetStrani(PredmetStrani predmetStrani) {
		this.predmetStrani = predmetStrani;
	}

	public String getOdobreno() {
		return odobreno;
	}

	public void setOdobreno(String odobreno) {
		this.odobreno = odobreno;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
