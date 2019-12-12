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
import com.ftn.student.service.models.Korisnik;
import com.ftn.student.service.repository.KorisniciRepository;

@RestController
public class AdminRestKorisnik {
	
	@Autowired
	private KorisniciRepository repoKorisnici;
	
	@RequestMapping(value = "/korisnici", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Korisnik>> korisnici() {

		List<Korisnik> kor = repoKorisnici.findAll();
		
		return new ResponseEntity<List<Korisnik>>(kor, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addKorisnik", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addKorisnik(@Valid @RequestBody Korisnik request) {

		Optional<Korisnik> kor = repoKorisnici.findById(request.getUsername());
		if (kor.isPresent()) {
			return new ResponseEntity<String>("Korisnik vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoKorisnici.save(request);
		return new ResponseEntity<String>("Korisnik uspesno dodat!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateKorisnik", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updateKorisnik(@Valid @RequestBody Korisnik request) {

		repoKorisnici.save(request);
		return new ResponseEntity<String>("Korisnik uspesno izmenjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteKorisnik", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteKorisnik(@Valid @RequestBody Korisnik request) {

		Optional<Korisnik> kor = repoKorisnici.findById(request.getUsername());
		if (!kor.isPresent()) {
			return new ResponseEntity<String>("Korisnik ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoKorisnici.delete(request);
		return new ResponseEntity<String>("Korisnik uspesno obrisan!", HttpStatus.OK);
	}

}