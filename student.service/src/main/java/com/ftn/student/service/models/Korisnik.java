package com.ftn.student.service.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "korisnici")
@Table(name = "korisnici")
public class Korisnik {

	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "ime")
	private String ime;
	
	@Column(name = "prezime")
	private String prezime;
	
	@Column(name = "jmbg")
	private String jmbg;
	
	@Column(name = "datumrodjenja")
	private Date datumrodjenja;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "uloga")
	private Uloga uloga;

	public Korisnik() {
	}

	public Korisnik(String username, String password, String ime, String prezime, String jmbg, Date datumrodjenja,
			Uloga uloga) {
		super();
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.datumrodjenja = datumrodjenja;
		this.uloga = uloga;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

}
