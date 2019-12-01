package com.ftn.student.service.rest.responses;

import java.util.List;

import com.ftn.student.service.models.PredmetDomaci;
import com.ftn.student.service.models.Student;

public class StudentLoginResponse {
	
	private Student student;
	private List<PredmetDomaci> predmeti;
	
	public StudentLoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentLoginResponse(Student student, List<PredmetDomaci> predmeti) {
		super();
		this.student = student;
		this.predmeti = predmeti;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<PredmetDomaci> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<PredmetDomaci> predmeti) {
		this.predmeti = predmeti;
	}
	
}
