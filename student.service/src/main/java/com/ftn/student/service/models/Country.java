package com.ftn.student.service.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country {

	@Id
	@Column(name = "country_id")
	private Integer id;
	
	@Column(name = "country")
	private String country;

	@Column(name = "last_update")
	private Timestamp lastUpdate;
	
	public Country() {
		super();
	}

	public Country(Integer id, String country, Timestamp lastUpdate) {
		super();
		this.id = id;
		this.country = country;
		this.lastUpdate = lastUpdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
}
