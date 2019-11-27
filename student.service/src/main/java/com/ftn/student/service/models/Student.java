package com.ftn.student.service.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "studenti")
public class Student {
	
	@Id
	@Column(name = "brindeksa")
	private String brindeksa;
	
	@Column(name = "ime")
	private String ime;
	
	@Column(name = "prezime")
	private String prezime;
	
	@Column(name = "jmbg")
	private String jmbg;
	
	@Column(name = "datumrodjenja")
	private Date datumrodjenja;
	
	@Column(name = "email")
	private String email;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="studije", nullable=false)
	private StudijskiProgramDomaci studije;
	
	public Student() {}

	public Student(String brindeksa, String ime, String prezime, String jmbg, Date datumrodjenja, String email,
			StudijskiProgramDomaci studije) {
		super();
		this.brindeksa = brindeksa;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.datumrodjenja = datumrodjenja;
		this.email = email;
		this.studije = studije;
	}

	public String getBrindeksa() {
		return brindeksa;
	}

	public void setBrindeksa(String brindeksa) {
		this.brindeksa = brindeksa;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public Date getDatumrodjenja() {
		return datumrodjenja;
	}

	public void setDatumrodjenja(Date datumrodjenja) {
		this.datumrodjenja = datumrodjenja;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public StudijskiProgramDomaci getStudije() {
		return studije;
	}

	public void setStudije(StudijskiProgramDomaci studije) {
		this.studije = studije;
	}

}
