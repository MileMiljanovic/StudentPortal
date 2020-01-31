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
import com.ftn.student.service.models.PredmetDomaci;
import com.ftn.student.service.models.Zamena;
import com.ftn.student.service.repository.PredmetiDomaciRepository;
import com.ftn.student.service.repository.ZamenaRepository;


@RestController
public class AdminRestPredmetiDom {
	
	@Autowired
	private PredmetiDomaciRepository repoDomaci;
	
	@Autowired
	private ZamenaRepository repoZamena;
	
	private final Logger log = LoggerFactory.getLogger(AdminRestPredmetiDom.class);
	
	@RequestMapping(value = "/predDomaci", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<PredmetDomaci>> predDomaci() {

		List<PredmetDomaci> predDom = repoDomaci.findAll();
		
		return new ResponseEntity<List<PredmetDomaci>>(predDom, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/predDomaci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JsonResponse> addPredDomaci(@Valid @RequestBody PredmetDomaci request) {

		Optional<PredmetDomaci> predDom = repoDomaci.findById(request.getPredmetId());
		if (predDom.isPresent()) {
			log.error("Subject already exists!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Predmet (domaci) vec postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoDomaci.save(request);
		log.info("Subject successfully inserted!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Predmet (domaci) uspesno dodat!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/predDomaci/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JsonResponse> putPredDomaci(@Valid @RequestBody PredmetDomaci request) {

		Optional<PredmetDomaci> predDom = repoDomaci.findById(request.getPredmetId());
		if (!predDom.isPresent()) {
			log.error("Subject does not exist!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Predmet (domaci) ne postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoDomaci.save(request);
		log.info("Subject successfully updated!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Predmet (domaci) uspesno izmenjen!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/predDomaci/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<JsonResponse> deletePredDomaci(@PathVariable String id) {

		Optional<PredmetDomaci> predDom = repoDomaci.findById(id);
		if (!predDom.isPresent()) {
			log.error("Subject does not exist!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Predmet (domaci) ne postoji!"), HttpStatus.BAD_REQUEST);
		}
		
		List<Zamena> zamene = repoZamena.findByDomaci(predDom.get());
		for (Zamena z: zamene) {
			repoZamena.delete(z);
		}
		
		repoDomaci.delete(predDom.get());
		log.info("Subject successfully deleted!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Predmet (domaci) uspesno obrisan!"), HttpStatus.OK);
	}

}
