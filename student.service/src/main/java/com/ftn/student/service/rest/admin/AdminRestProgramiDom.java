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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.student.service.models.Korisnik;
import com.ftn.student.service.models.StudijskiProgramDomaci;
import com.ftn.student.service.models.Uloga;
import com.ftn.student.service.repository.KorisniciRepository;
import com.ftn.student.service.repository.SProgramDomaciRepository;
import com.ftn.student.service.rest.responsesadmin.AdminProgramDResponse;

@RestController
public class AdminRestProgramiDom {
	
	@Autowired
	private SProgramDomaciRepository repoDomaci;
	
	@Autowired
	private KorisniciRepository repoKorisnici;
	
	private final Logger log = LoggerFactory.getLogger(AdminRestProgramiDom.class);
	
	@RequestMapping(value = "/admin/progDomaci", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<AdminProgramDResponse> progDomaci() {

		List<StudijskiProgramDomaci> progDom = repoDomaci.findAll();
		List<Korisnik> sefovi = repoKorisnici.findByRole(Uloga.SEF);
		
		AdminProgramDResponse response = new AdminProgramDResponse(progDom, sefovi);
		
		return new ResponseEntity<AdminProgramDResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin/addProgDomaci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addProgDomaci(@Valid @RequestBody StudijskiProgramDomaci request) {

		Optional<StudijskiProgramDomaci> progDom = repoDomaci.findById(request.getNaziv());
		if (progDom.isPresent()) {
			log.error("Program already exists!");
			return new ResponseEntity<String>("Program (domaci) vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDomaci.save(request);
		log.info("Program successfully inserted!");
		return new ResponseEntity<String>("Program (domaci) uspesno dodat!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin/updateProgDomaci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updateProgDomaci(@Valid @RequestBody StudijskiProgramDomaci request) {

		Optional<StudijskiProgramDomaci> progDom = repoDomaci.findById(request.getNaziv());
		if (!progDom.isPresent()) {
			log.error("Program does not exist!");
			return new ResponseEntity<String>("Program (domaci) ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDomaci.save(request);
		log.info("Program successfully updated!");
		return new ResponseEntity<String>("Program (domaci) uspesno izmenjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin/deleteProgDomaci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteProgDomaci(@Valid @RequestBody StudijskiProgramDomaci request) {

		Optional<StudijskiProgramDomaci> progDom = repoDomaci.findById(request.getNaziv());
		if (!progDom.isPresent()) {
			log.error("Program does not exist!");
			return new ResponseEntity<String>("Program (domaci) ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDomaci.delete(request);
		log.info("Program successfully deleted!");
		return new ResponseEntity<String>("Program (domaci) uspesno obrisan!", HttpStatus.OK);
	}

}
