package com.ftn.student.service.rest.requests;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.ftn.student.service.models.Student;
import com.ftn.student.service.models.StudijskiProgramStrani;
import com.ftn.student.service.models.Zamena;

public class SubmitFormRequest {
	
	@NotNull(message="Nedostaje student")
	private Student student;
	
	@NotNull(message="Nedostaje strani studijski program")
	private StudijskiProgramStrani programStrani;
	
	@NotNull(message="Nedostaje id formulara")
	private String formularId;

	@NotNull(message="Nedostaje bar jedna zamena")
	private List<Zamena> zamene;

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
	
	public String getFormularId() {
		return formularId;
	}

	public void setFormularId(String formularId) {
		this.formularId = formularId;
	}

	public List<Zamena> getZamene() {
		return zamene;
	}

	public void setZamene(List<Zamena> zamene) {
		this.zamene = zamene;
	}

}
