package com.ftn.student.service.rest.responsesadmin;

import java.util.List;

import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Student;
import com.ftn.student.service.models.StudijskiProgramStrani;

public class AdminFormularResponse {
	
	private List<Formular> formulari;
	private List<Student> studenti;
	private List<StudijskiProgramStrani> programiStrani;
	
	public AdminFormularResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AdminFormularResponse(List<Formular> formulari, List<Student> studenti,
			List<StudijskiProgramStrani> programiStrani) {
		super();
		this.formulari = formulari;
		this.studenti = studenti;
		this.programiStrani = programiStrani;
	}

	public List<Formular> getFormulari() {
		return formulari;
	}

	public void setFormulari(List<Formular> formulari) {
		this.formulari = formulari;
	}

	public List<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}

	public List<StudijskiProgramStrani> getProgramiStrani() {
		return programiStrani;
	}

	public void setProgramiStrani(List<StudijskiProgramStrani> programiStrani) {
		this.programiStrani = programiStrani;
	}

}
