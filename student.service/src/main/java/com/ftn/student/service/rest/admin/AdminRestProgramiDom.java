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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.student.service.errorhandling.JsonResponse;
import com.ftn.student.service.models.StudijskiProgramDomaci;
import com.ftn.student.service.repository.SProgramDomaciRepository;

@RestController
public class AdminRestProgramiDom {
	
	@Autowired
	private SProgramDomaciRepository repoDomaci;
	
	private final Logger log = LoggerFactory.getLogger(AdminRestProgramiDom.class);
	
	@RequestMapping(value = "/progDomaci", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<StudijskiProgramDomaci>> progDomaci() {

		List<StudijskiProgramDomaci> progDom = repoDomaci.findAll();
		
		return new ResponseEntity<List<StudijskiProgramDomaci>>(progDom, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/progDomaci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JsonResponse> addProgDomaci(@Valid @RequestBody StudijskiProgramDomaci request) {

		Optional<StudijskiProgramDomaci> progDom = repoDomaci.findById(request.getNaziv());
		if (progDom.isPresent()) {
			log.error("Program already exists!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Program (domaci) vec postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoDomaci.save(request);
		log.info("Program successfully inserted!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Program (domaci) uspesno dodat!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/progDomaci/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JsonResponse> putProgDomaci(@Valid @RequestBody StudijskiProgramDomaci request) {

		Optional<StudijskiProgramDomaci> progDom = repoDomaci.findById(request.getNaziv());
		if (!progDom.isPresent()) {
			log.error("Program does not exist!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Program (domaci) ne postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoDomaci.save(request);
		log.info("Program successfully updated!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Program (domaci) uspesno izmenjen!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/progDomaci/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<JsonResponse> deleteProgDomaci(@PathVariable String id) {

		Optional<StudijskiProgramDomaci> progDom = repoDomaci.findById(id);
		if (!progDom.isPresent()) {
			log.error("Program does not exist!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Program (domaci) ne postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoDomaci.delete(progDom.get());
		log.info("Program successfully deleted!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Program (domaci) uspesno obrisan!"), HttpStatus.OK);
	}

}
