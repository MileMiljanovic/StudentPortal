package com.ftn.student.service.rest.responsesadmin;

import java.util.List;

import com.ftn.student.service.models.PredmetStrani;
import com.ftn.student.service.models.StudijskiProgramStrani;

public class AdminPredmetSResponse {
	
	private List<PredmetStrani> predmeti;
	private List<StudijskiProgramStrani> programi;
	
	public AdminPredmetSResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AdminPredmetSResponse(List<PredmetStrani> predmeti, List<StudijskiProgramStrani> programi) {
		super();
		this.predmeti = predmeti;
		this.programi = programi;
	}

	public List<PredmetStrani> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<PredmetStrani> predmeti) {
		this.predmeti = predmeti;
	}

	public List<StudijskiProgramStrani> getProgrami() {
		return programi;
	}

	public void setProgrami(List<StudijskiProgramStrani> programi) {
		this.programi = programi;
	}
	

}
