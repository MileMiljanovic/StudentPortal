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

import com.ftn.student.service.models.Nastavnik;
import com.ftn.student.service.repository.NastavnikRepository;

@RestController
public class AdminRestNastavnik {

	@Autowired
	private NastavnikRepository repoNastavnici;
	
	@RequestMapping(value = "/nastavnici", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Nastavnik>> nastavnici() {

		List<Nastavnik> nas = repoNastavnici.findAll();
		
		return new ResponseEntity<List<Nastavnik>>(nas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addNastavnik", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addNastavnik(@Valid @RequestBody Nastavnik request) {

		Optional<Nastavnik> nas = repoNastavnici.findById(request.getNastavnikid());
		if (nas.isPresent()) {
			return new ResponseEntity<String>("Nastavnik vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoNastavnici.save(request);
		return new ResponseEntity<String>("Nastavnik uspesno dodat!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateNastavnik", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updateNastavnik(@Valid @RequestBody Nastavnik request) {

		repoNastavnici.save(request);
		return new ResponseEntity<String>("Nastavnik uspesno izmenjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteNastavnik", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteNastavnik(@Valid @RequestBody Nastavnik request) {

		Optional<Nastavnik> nas = repoNastavnici.findById(request.getNastavnikid());
		if (!nas.isPresent()) {
			return new ResponseEntity<String>("Nastavnik ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoNastavnici.delete(request);
		return new ResponseEntity<String>("Nastavnik uspesno obrisan!", HttpStatus.OK);
	}
}
