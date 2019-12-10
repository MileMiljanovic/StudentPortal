package com.ftn.student.service.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "studijski_programi_domaci")
@Table(name = "studijski_programi_domaci")
public class StudijskiProgramDomaci {
	
	@Id
	@Column(name = "naziv")
	private String naziv;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="departman", nullable=false)
	private Departman departman;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="sef", nullable=false)
	private Korisnik sef;

	public StudijskiProgramDomaci() {}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Departman getDepartman() {
		return departman;
	}

	public void setDepartman(Departman departman) {
		this.departman = departman;
	}

	public Korisnik getSef() {
		return sef;
	}

	public void setSef(Korisnik sef) {
		this.sef = sef;
	}

}
