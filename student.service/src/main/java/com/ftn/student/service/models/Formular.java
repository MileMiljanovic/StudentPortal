package com.ftn.student.service.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity(name = "formulari")
@Table(name = "formulari")
public class Formular {

	@Id
	@Column(name = "idformular")
	private String idformular;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="student")
	private Student student;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="program_strani")
	private StudijskiProgramStrani programStrani;
	
	@Column(name = "odobrenjesef")
	private String odobrenjeSef;
	
	@Column(name = "odobrenjekoord")
	private String odobrenjeKoord;
	
	@Column(name = "datum")
	private Timestamp datum;
	
	@OneToMany(mappedBy="formular", fetch = FetchType.EAGER)
    private List<Zamena> zamene;

	public Formular() {}

	public Formular(String idformular, Student student, StudijskiProgramStrani programStrani, String odobrenjeSef,
			String odobrenjeKoord, Timestamp datum, List<Zamena> zamene) {
		this.idformular = idformular;
		this.student = student;
		this.programStrani = programStrani;
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

	public StudijskiProgramStrani getProgramStrani() {
		return programStrani;
	}

	public void setProgramStrani(StudijskiProgramStrani programStrani) {
		this.programStrani = programStrani;
	}

	public String getOdobrenjeSef() {
		return odobrenjeSef;
	}

	public void setOdobrenjeSef(String odobrenjeSef) {
		this.odobrenjeSef = odobrenjeSef;
	}

	public String getOdobrenjeKoord() {
		return odobrenjeKoord;
	}

	public void setOdobrenjeKoord(String odobrenjeKoord) {
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
