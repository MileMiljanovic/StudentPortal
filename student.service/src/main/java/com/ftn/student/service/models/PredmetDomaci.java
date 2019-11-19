package com.ftn.student.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "predmeti_domaci")
@IdClass(PredmetDomaciID.class)
public class PredmetDomaci {

	@Id
	@Column(name = "naziv")
	private String naziv;
	
	@Id
	@ManyToOne
	@JoinColumn(name="studijski_program", nullable=false)
	private StudijskiProgramDomaci program;
	
	@Column(name = "espb")
	private int espb;
	
	@OneToOne
	@JoinColumn(name="nastavnik", nullable=false)
	private Nastavnik nastavnik;
	
	public PredmetDomaci() {}

	public PredmetDomaci(String naziv, StudijskiProgramDomaci program, int espb, Nastavnik nastavnik) {
		super();
		this.naziv = naziv;
		this.program = program;
		this.espb = espb;
		this.nastavnik = nastavnik;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public StudijskiProgramDomaci getProgram() {
		return program;
	}

	public void setProgram(StudijskiProgramDomaci program) {
		this.program = program;
	}

	public int getEspb() {
		return espb;
	}

	public void setEspb(int espb) {
		this.espb = espb;
	}

	public Nastavnik getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(Nastavnik nastavnik) {
		this.nastavnik = nastavnik;
	}

}
