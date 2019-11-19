package com.ftn.student.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "predmeti_strani")
@IdClass(PredmetStraniID.class)
public class PredmetStrani {
	
	@Id
	@Column(name = "naziv")
	private String naziv;
	
	@Id
	@OneToOne
	@JoinColumn(name="studijski_program_strani", nullable=false)
	private StudijskiProgramStrani program;
	
	@Column(name = "espb")
	private int espb;
	
	public PredmetStrani() {}

	public PredmetStrani(String naziv, StudijskiProgramStrani program, int espb) {
		super();
		this.naziv = naziv;
		this.program = program;
		this.espb = espb;
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
