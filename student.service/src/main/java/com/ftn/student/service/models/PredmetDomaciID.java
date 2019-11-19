package com.ftn.student.service.models;

import java.io.Serializable;

public class PredmetDomaciID implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String naziv;
	private StudijskiProgramDomaci program;
	
	
	public PredmetDomaciID() {}

	public PredmetDomaciID(String naziv, StudijskiProgramDomaci program) {
		super();
		this.naziv = naziv;
		this.program = program;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		result = prime * result + ((program == null) ? 0 : program.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PredmetDomaciID other = (PredmetDomaciID) obj;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		if (program == null) {
			if (other.program != null)
				return false;
		} else if (!program.equals(other.program))
			return false;
		return true;
	}
	
	


}
