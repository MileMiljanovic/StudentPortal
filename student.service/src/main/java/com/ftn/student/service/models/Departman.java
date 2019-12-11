package com.ftn.student.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "departmani")
@Table(name = "departmani")
public class Departman {
	
	@Id
	@Column(name = "departmanid")
	private String departmanId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="koordinator")
	private Korisnik koordinator;
	
	public Departman() {}

	public Departman(String departmanId, Korisnik koordinator) {
		super();
		this.departmanId = departmanId;
		this.koordinator = koordinator;
	}

	public String getDepartmanId() {
		return departmanId;
	}

	public void setDepartmanId(String departmanId) {
		this.departmanId = departmanId;
	}

	public Korisnik getKoordinator() {
		return koordinator;
	}

	public void setKoordinator(Korisnik koordinator) {
		this.koordinator = koordinator;
	}
	
	
	

}
