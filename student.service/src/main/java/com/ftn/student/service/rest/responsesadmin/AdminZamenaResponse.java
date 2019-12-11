package com.ftn.student.service.rest.responsesadmin;

import java.util.List;

import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.PredmetDomaci;
import com.ftn.student.service.models.PredmetStrani;
import com.ftn.student.service.models.Zamena;


public class AdminZamenaResponse {
	
	private List<Zamena> Zamene;
	private List<Formular> formulari;
	private List<PredmetDomaci> predmetDomaci;
	private List<PredmetStrani> predmetStrani;
	
	public AdminZamenaResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AdminZamenaResponse(List<Zamena> zamene, List<Formular> formulari, List<PredmetDomaci> predmetDomaci,
			List<PredmetStrani> predmetStrani) {
		super();
		Zamene = zamene;
		this.formulari = formulari;
		this.predmetDomaci = predmetDomaci;
		this.predmetStrani = predmetStrani;
	}

	public List<Zamena> getZamene() {
		return Zamene;
	}

	public void setZamene(List<Zamena> zamene) {
		Zamene = zamene;
	}

	public List<Formular> getFormulari() {
		return formulari;
	}

	public void setFormulari(List<Formular> formulari) {
		this.formulari = formulari;
	}

	public List<PredmetDomaci> getPredmetDomaci() {
		return predmetDomaci;
	}

	public void setPredmetDomaci(List<PredmetDomaci> predmetDomaci) {
		this.predmetDomaci = predmetDomaci;
	}

	public List<PredmetStrani> getPredmetStrani() {
		return predmetStrani;
	}

	public void setPredmetStrani(List<PredmetStrani> predmetStrani) {
		this.predmetStrani = predmetStrani;
	}

}
