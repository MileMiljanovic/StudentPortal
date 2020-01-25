package com.ftn.student.service.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "nastavnici")
@Table(name = "nastavnici")
public class Nastavnik {
	
	@Id
	@Column(name = "nastavnikid")
	private String nastavnikid;
	
	@Column(name = "ime")
	private String ime;
	
	@Column(name = "prezime")
	private String prezime;
	
	@Column(name = "jmbg")
	private String jmbg;
	
	@Column(name = "datumrodjenja")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date datumrodjenja;
	
	@Column(name = "email")
	private String email;

	public Nastavnik() {}

	public Nastavnik(String nastavnikid, String ime, String prezime, String jmbg, Date datumrodjenja, String email) {
		super();
		this.nastavnikid = nastavnikid;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.datumrodjenja = datumrodjenja;
		this.email = email;
	}

	public String getNastavnikid() {
		return nastavnikid;
	}

	public void setNastavnikid(String nastavnikid) {
		this.nastavnikid = nastavnikid;
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
	
}
