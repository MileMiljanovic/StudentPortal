package com.ftn.student.service.rest.responses;

import java.util.List;

import com.ftn.student.service.models.Student;
import com.ftn.student.service.models.StudijskiProgramStrani;

public class StudentLoginResponse {
	
	private Student student;
	private List<StudijskiProgramStrani> programiStrani;
	
	public StudentLoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentLoginResponse(Student student, List<StudijskiProgramStrani> programiStrani) {
		super();
		this.student = student;
		this.programiStrani = programiStrani;
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

	
	
}
