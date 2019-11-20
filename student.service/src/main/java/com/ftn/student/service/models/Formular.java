package com.ftn.student.service.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "formulari")
public class Formular {
	
	@Id
	@Column(name = "idformular")
	private String idformular;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="student")
	private Student student;
	
	@Column(name = "odobrenjesef")
	private char odobrenjeSef;
	
	@Column(name = "odobrenjekoord")
	private char odobrenjeKoord;
	
	@Column(name = "datum")
	private Timestamp datum;
	
	@OneToMany(mappedBy="formular", fetch = FetchType.EAGER)
    private List<Zamena> zamene;

	public Formular() {}

	public Formular(String idformular, Student student, char odobrenjeSef, char odobrenjeKoord, Timestamp datum, List<Zamena> zamene) {
		super();
		this.idformular = idformular;
		this.student = student;
		this.odobrenjeSef = odobrenjeSef;
		this.odobrenjeKoord = odobrenjeKoord;
		this.datum = datum;
		this.zamene = zamene;
	}

	public String getIdformular() {
		return idformular;
	}

	public void setIdformular(String idformular) {
		this.idformular = idformular;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public char getOdobrenjeSef() {
		return odobrenjeSef;
	}

	public void setOdobrenjeSef(char odobrenjeSef) {
		this.odobrenjeSef = odobrenjeSef;
	}

	public char getOdobrenjeKoord() {
		return odobrenjeKoord;
	}

	public void setOdobrenjeKoord(char odobrenjeKoord) {
		this.odobrenjeKoord = odobrenjeKoord;
	}

	public Timestamp getDatum() {
		return datum;
	}

	public void setDatum(Timestamp datum) {
		this.datum = datum;
	}
	
	public List<Zamena> getZamene() {
		return zamene;
	}

	public void setZamene(List<Zamena> zamene) {
		this.zamene = zamene;
	}

}
