package com.ftn.student.service.rest.responsesadmin;

import java.util.List;

import com.ftn.student.service.models.Student;
import com.ftn.student.service.models.StudijskiProgramDomaci;

public class AdminStudentResponse {
	
	private List<Student> studenti;
	private List<StudijskiProgramDomaci> programi;
	
	public AdminStudentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminStudentResponse(List<Student> studenti, List<StudijskiProgramDomaci> programi) {
		super();
		this.studenti = studenti;
		this.programi = programi;
	}

	public List<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}

	public List<StudijskiProgramDomaci> getProgrami() {
		return programi;
	}

	public void setProgrami(List<StudijskiProgramDomaci> programi) {
		this.programi = programi;
	}

}
