package com.ftn.student.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "formulari")
public class Formular {
	
	@Id
	@Column(name = "idformular")
	private String idformular;
	
	@ManyToOne
	@JoinColumn(name="student", nullable=false)
	private Student student;
	
	@Column(name = "odobrenjesef")
	private char odobrenjeSef;
	
	@Column(name = "odobrenjekoord")
	private char odobrenjeKoord;

	public Formular() {}

	public Formular(String idformular, Student student, char odobrenjeSef, char odobrenjeKoord) {
		super();
		this.idformular = idformular;
		this.student = student;
		this.odobrenjeSef = odobrenjeSef;
		this.odobrenjeKoord = odobrenjeKoord;
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

}
