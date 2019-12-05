package com.ftn.student.service.rest.requests;

import javax.validation.constraints.NotNull;

import com.ftn.student.service.models.Student;
import com.ftn.student.service.models.StudijskiProgramStrani;


public class ConfirmProgramRequest {
	
	@NotNull(message="Nedostaje student")
	private Student student;

	@NotNull(message="Odaberite strani studijski program")
	private StudijskiProgramStrani programStrani;

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

}
