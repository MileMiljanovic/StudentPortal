package com.ftn.student.service.rest.responses;

import java.util.List;

import com.ftn.student.service.models.PredmetDomaci;
import com.ftn.student.service.models.PredmetStrani;
import com.ftn.student.service.models.Student;
import com.ftn.student.service.models.StudijskiProgramStrani;

public class ConfirmProgramResponse {
	
	private Student student;
	private StudijskiProgramStrani programStrani;
	private List<PredmetDomaci> predmetiDomaci;
	private List<PredmetStrani> predmetiStrani;
	private String formularId;
	
	public ConfirmProgramResponse() {}

	public ConfirmProgramResponse(Student student, StudijskiProgramStrani programStrani,
			List<PredmetDomaci> predmetiDomaci, List<PredmetStrani> predmetiStrani, String formularId) {
		super();
		this.student = student;
		this.programStrani = programStrani;
		this.predmetiDomaci = predmetiDomaci;
		this.predmetiStrani = predmetiStrani;
		this.formularId = formularId;
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

	public List<PredmetDomaci> getPredmetiDomaci() {
		return predmetiDomaci;
	}

	public void setPredmetiDomaci(List<PredmetDomaci> predmetiDomaci) {
		this.predmetiDomaci = predmetiDomaci;
	}

	public List<PredmetStrani> getPredmetiStrani() {
		return predmetiStrani;
	}

	public void setPredmetiStrani(List<PredmetStrani> predmetiStrani) {
		this.predmetiStrani = predmetiStrani;
	}

	public String getFormularId() {
		return formularId;
	}

	public void setFormularId(String formularId) {
		this.formularId = formularId;
	}


}
