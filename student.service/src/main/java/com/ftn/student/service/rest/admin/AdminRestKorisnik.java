package com.ftn.student.service.rest.admin;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.student.service.errorhandling.JsonResponse;
import com.ftn.student.service.models.KorisnikAdm;
import com.ftn.student.service.repository.KorisniciAdmRepository;

@RestController
public class AdminRestKorisnik {
	
	@Autowired
	private KorisniciAdmRepository repoKorisnici;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private final Logger log = LoggerFactory.getLogger(AdminRestKorisnik.class);
	
	@RequestMapping(value = "/korisnik", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<KorisnikAdm>> korisnici() {

		List<KorisnikAdm> kor = repoKorisnici.findAll();
		return new ResponseEntity<List<KorisnikAdm>>(kor, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/korisnik", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JsonResponse> addKorisnik(@Valid @RequestBody KorisnikAdm request) {

		Optional<KorisnikAdm> kor = repoKorisnici.findById(request.getUsername());
		if (kor.isPresent()) {
			log.error("User already exists!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Korisnik vec postoji!"), HttpStatus.BAD_REQUEST);
		}
		String encodedPassword = passwordEncoder.encode(request.getPassword());
		request.setPassword(encodedPassword);
		repoKorisnici.save(request);
		log.info("User successfully added!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Korisnik uspesno dodat!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/korisnik/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JsonResponse> putKorisnik(@Valid @RequestBody KorisnikAdm request) {

		Optional<KorisnikAdm> kor = repoKorisnici.findById(request.getUsername());
		if (!kor.isPresent()) {
			log.error("User does not exist!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Korisnik ne postoji!"), HttpStatus.BAD_REQUEST);
		}
		String encodedPassword = passwordEncoder.encode(request.getPassword());
		request.setPassword(encodedPassword);
		repoKorisnici.save(request);
		log.info("User successfully updated!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Korisnik uspesno izmenjen!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/korisnik/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<JsonResponse> deleteKorisnik(@PathVariable String id) {

		Optional<KorisnikAdm> kor = repoKorisnici.findById(id);
		if (!kor.isPresent()) {
			log.error("User does not exist!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Korisnik ne postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoKorisnici.delete(kor.get());
		log.info("User successfully deleted!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Korisnik uspesno obrisan!"), HttpStatus.OK);
	}

}
