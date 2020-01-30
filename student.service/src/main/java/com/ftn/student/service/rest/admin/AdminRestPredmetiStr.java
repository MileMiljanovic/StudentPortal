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
import com.ftn.student.service.models.PredmetStrani;
import com.ftn.student.service.repository.PredmetiStraniRepository;

@RestController
public class AdminRestPredmetiStr {
	
	@Autowired
	private PredmetiStraniRepository repoStrani;
	
	private final Logger log = LoggerFactory.getLogger(AdminRestPredmetiStr.class);
	
	@RequestMapping(value = "/predStrani", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<PredmetStrani>> predStrani() {

		List<PredmetStrani> predStr = repoStrani.findAll();
		
		return new ResponseEntity<List<PredmetStrani>>(predStr, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/predStrani", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JsonResponse> addPredStrani(@Valid @RequestBody PredmetStrani request) {

		Optional<PredmetStrani> predStr = repoStrani.findById(request.getPredmetId());
		if (predStr.isPresent()) {
			log.error("Subject already exists!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Predmet (strani) vec postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoStrani.save(request);
		log.info("Subject successfully inserted!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Predmet (strani) uspesno dodat!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/predStrani/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JsonResponse> putPredStrani(@Valid @RequestBody PredmetStrani request) {

		Optional<PredmetStrani> predStr = repoStrani.findById(request.getPredmetId());
		if (!predStr.isPresent()) {
			log.error("Subject does not exist!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Predmet (strani) ne postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoStrani.save(request);
		log.info("Subject successfully updated!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Predmet (strani) uspesno izmenjen!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/predStrani/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<JsonResponse> deletePredStrani(@PathVariable String id) {

		Optional<PredmetStrani> predStr = repoStrani.findById(id);
		if (!predStr.isPresent()) {
			log.error("Subject does not exist!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Predmet (strani) ne postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoStrani.delete(predStr.get());
		log.info("Subject successfully deleted!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Predmet (strani) uspesno obrisan!"), HttpStatus.OK);
	}

}
