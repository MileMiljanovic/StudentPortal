package com.ftn.student.service.rest.admin;

import java.util.List;
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
import com.ftn.student.service.repository.FormularRepository;

@RestController
public class AdminRestFormular {
	
	@Autowired
	private FormularRepository repoFormular;
	
	@RequestMapping(value = "/formulari", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Formular>> formulari() {

		List<Formular> form = repoFormular.findAll();
		
		return new ResponseEntity<List<Formular>>(form, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addFormular", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addFormular(@Valid @RequestBody Formular request) {

		Optional<Formular> form = repoFormular.findById(request.getIdformular());
		if (form.isPresent()) {
			return new ResponseEntity<String>("Formular vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoFormular.save(request);
		return new ResponseEntity<String>("Formular uspesno dodat!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateFormular", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updateFormular(@Valid @RequestBody Formular request) {

		repoFormular.save(request);
		return new ResponseEntity<String>("Formular uspesno izmenjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteFormular", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteFormular(@Valid @RequestBody Formular request) {

		Optional<Formular> form = repoFormular.findById(request.getIdformular());
		if (!form.isPresent()) {
			return new ResponseEntity<String>("Formular ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoFormular.delete(request);
		return new ResponseEntity<String>("Formular uspesno obrisan!", HttpStatus.OK);
	}

}
