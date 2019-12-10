package com.ftn.student.service.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "predmeti_strani")
@Table(name = "predmeti_strani")
public class PredmetStrani {
	
	@Id
	@Column(name = "predmetid")
	private String predmetId;

	@Column(name = "naziv")
	private String naziv;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="studijski_program_strani", nullable=false)
	private StudijskiProgramStrani program;
	
	@Column(name = "espb")
	private int espb;
	
	public PredmetStrani() {}

	
	
	public PredmetStrani(String predmetId, String naziv, StudijskiProgramStrani program, int espb) {
		super();
		this.predmetId = predmetId;
		this.naziv = naziv;
		this.program = program;
		this.espb = espb;
	}



	public String getPredmetId() {
		return predmetId;
	}

	public void setPredmetId(String predmetId) {
		this.predmetId = predmetId;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public StudijskiProgramStrani getProgram() {
		return program;
	}

	public void setProgram(StudijskiProgramStrani program) {
		this.program = program;
	}

	public int getEspb() {
		return espb;
	}

	public void setEspb(int espb) {
		this.espb = espb;
	}

}
