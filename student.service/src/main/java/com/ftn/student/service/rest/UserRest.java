package com.ftn.student.service.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Korisnik;
import com.ftn.student.service.repository.FormularRepository;
import com.ftn.student.service.rest.requests.ConfirmationRequest;
import com.ftn.student.service.rest.requests.UserLoginRequest;

@RestController
public class UserRest {
	
	@Autowired
	private FormularRepository repoFormular;
	
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Korisnik> userLogin(@Valid @RequestBody UserLoginRequest request) {

		
		//return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<Korisnik>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/coordinatorConfirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> coordinatorConfirm(@Valid @RequestBody ConfirmationRequest request) {

		Optional<Formular> fr = repoFormular.findById(request.getFormularId());
		
		if (!fr.isPresent()) {
			return new ResponseEntity<String>("Greska! Formular nije pronadjen!", HttpStatus.NOT_FOUND);
		}
		
		Formular f = repoFormular.findById(request.getFormularId()).get();
		f.setOdobrenjeKoord(request.getOdgovor());
		repoFormular.save(f);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/headConfirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> headConfirm(@Valid @RequestBody ConfirmationRequest request) {

		Optional<Formular> fr = repoFormular.findById(request.getFormularId());
		
		if (!fr.isPresent()) {
			return new ResponseEntity<String>("Greska! Formular nije pronadjen!", HttpStatus.NOT_FOUND);
		}
		
		Formular f = repoFormular.findById(request.getFormularId()).get();		
		f.setOdobrenjeSef(request.getOdgovor());
		repoFormular.save(f);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
