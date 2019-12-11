package com.ftn.student.service.rest.responsesadmin;

import java.util.List;

import com.ftn.student.service.models.Nastavnik;
import com.ftn.student.service.models.PredmetDomaci;
import com.ftn.student.service.models.StudijskiProgramDomaci;

public class AdminPredmetDResponse {
	
	private List<PredmetDomaci> predmeti;
	private List<StudijskiProgramDomaci> programi;
	private List<Nastavnik> nastavnici;
	
	public AdminPredmetDResponse() {}

	public AdminPredmetDResponse(List<PredmetDomaci> predmeti, List<StudijskiProgramDomaci> programi,
			List<Nastavnik> nastavnici) {
		super();
		this.predmeti = predmeti;
		this.programi = programi;
		this.nastavnici = nastavnici;
	}

	public List<PredmetDomaci> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<PredmetDomaci> predmeti) {
		this.predmeti = predmeti;
	}

	public List<StudijskiProgramDomaci> getProgrami() {
		return programi;
	}

	public void setProgrami(List<StudijskiProgramDomaci> programi) {
		this.programi = programi;
	}

	public List<Nastavnik> getNastavnici() {
		return nastavnici;
	}

	public void setNastavnici(List<Nastavnik> nastavnici) {
		this.nastavnici = nastavnici;
	}

}
