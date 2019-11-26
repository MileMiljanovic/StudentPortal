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
public class UserRest {
	
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> userLogin(HttpEntity<String> httpEntity) {

		System.out.println("USER LOGIN");
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/coordinatorConfirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> coordinatorConfirm(HttpEntity<String> httpEntity) {

		System.out.println("COORD CONF");
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/headConfirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> headConfirm(HttpEntity<String> httpEntity) {

		System.out.println("HEAD CONF");
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
