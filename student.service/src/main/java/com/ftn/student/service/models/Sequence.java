package com.ftn.student.service.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "sequence")
@Table(name = "sequence")
public class Sequence {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long counter;

	public Sequence() {
		super();
	}

	public Sequence(Long counter) {
		super();
		this.counter = counter;
	}

	public Long getCounter() {
		return counter;
	}

	public void setCounter(Long counter) {
		this.counter = counter;
	}
	
	

}
