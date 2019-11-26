package com.ftn.student.service.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentRest {
	
	@RequestMapping(value = "/studentLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> studentLogin(HttpEntity<String> httpEntity) {

		System.out.println("STUDENT LOGIN");
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/submitForm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> submitForm(HttpEntity<String> httpEntity) {

		System.out.println("SUBMIT FORM");
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cancelForm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> cancelForm(HttpEntity<String> httpEntity) {

		System.out.println("CANCEL FORM");
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
