package com.ftn.student.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "zamena_token")
@Table(name = "zamena_token")
public class ZamenaToken {
	
	@Id
	@Column(name = "idzamene")
	private String idZamene;
	
	@Column(name = "token")
	private String token;

	public ZamenaToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ZamenaToken(String idZamene, String token) {
		super();
		this.idZamene = idZamene;
		this.token = token;
	}

	public String getIdZamene() {
		return idZamene;
	}

	public void setIdZamene(String idZamene) {
		this.idZamene = idZamene;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
