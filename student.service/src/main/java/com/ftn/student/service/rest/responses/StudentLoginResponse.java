package com.ftn.student.service.rest.responses;

import java.util.List;

import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Student;
import com.ftn.student.service.models.StudijskiProgramStrani;

public class StudentLoginResponse {
	
	private Student student;
	private List<StudijskiProgramStrani> programiStrani;
	private Formular f;
	
	public StudentLoginResponse() {
		super();
	}

	public StudentLoginResponse(Student student, List<StudijskiProgramStrani> programiStrani, Formular f) {
		super();
		this.student = student;
		this.programiStrani = programiStrani;
		this.f = f;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<StudijskiProgramStrani> getProgramiStrani() {
		return programiStrani;
	}

	public void setProgramiStrani(List<StudijskiProgramStrani> programiStrani) {
		this.programiStrani = programiStrani;
	}

	public Formular getF() {
		return f;
	}

	public void setF(Formular f) {
		this.f = f;
	}

	
}
