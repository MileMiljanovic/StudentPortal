package com.ftn.student.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "zamene")
public class Zamena {
	
	@Id
	@Column(name = "idzamena")
	private String idzamena;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idformulara", nullable=false)
	private Formular formular;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		  @JoinColumn(name = "domaci", insertable = false, updatable = false),
		  @JoinColumn(name = "domaciprogram", insertable = false, updatable = false)
		})
	private PredmetDomaci predmetDomaci;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="domaciprogram", nullable=false)
	private StudijskiProgramDomaci studijskiProgramDomaci;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		  @JoinColumn(name = "strani", insertable = false, updatable = false),
		  @JoinColumn(name = "straniprogram", insertable = false, updatable = false)
		})
	private PredmetStrani predmetStrani;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="straniprogram", nullable=false)
	private StudijskiProgramStrani studijskiProgramStrani;
	
	@Column(name = "odobreno")
	private char odobreno;

	public Zamena() {}

	public Zamena(String idzamena, Formular formular, PredmetDomaci predmetDomaci,
			StudijskiProgramDomaci studijskiProgramDomaci, PredmetStrani predmetStrani,
			StudijskiProgramStrani studijskiProgramStrani, char odobreno) {
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

	public Formular getFormular() {
		return formular;
	}

	public void setFormular(Formular formular) {
		this.formular = formular;
	}

	public PredmetDomaci getPredmetDomaci() {
		return predmetDomaci;
	}

	public void setPredmetDomaci(PredmetDomaci predmetDomaci) {
		this.predmetDomaci = predmetDomaci;
	}

	public StudijskiProgramDomaci getStudijskiProgramDomaci() {
		return studijskiProgramDomaci;
	}

	public void setStudijskiProgramDomaci(StudijskiProgramDomaci studijskiProgramDomaci) {
		this.studijskiProgramDomaci = studijskiProgramDomaci;
	}

	public PredmetStrani getPredmetStrani() {
		return predmetStrani;
	}

	public void setPredmetStrani(PredmetStrani predmetStrani) {
		this.predmetStrani = predmetStrani;
	}

	public StudijskiProgramStrani getStudijskiProgramStrani() {
		return studijskiProgramStrani;
	}

	public void setStudijskiProgramStrani(StudijskiProgramStrani studijskiProgramStrani) {
		this.studijskiProgramStrani = studijskiProgramStrani;
	}

	public char getOdobreno() {
		return odobreno;
	}

	public void setOdobreno(char odobreno) {
		this.odobreno = odobreno;
	}
	
	
	
	

}
