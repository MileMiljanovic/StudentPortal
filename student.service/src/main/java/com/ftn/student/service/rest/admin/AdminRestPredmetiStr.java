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

import com.ftn.student.service.models.PredmetStrani;
import com.ftn.student.service.repository.PredmetiStraniRepository;

@RestController
public class AdminRestPredmetiStr {
	
	@Autowired
	private PredmetiStraniRepository repoStrani;
	
	@RequestMapping(value = "/predStrani", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<PredmetStrani>> predStrani() {

		List<PredmetStrani> predStr = repoStrani.findAll();
		
		return new ResponseEntity<List<PredmetStrani>>(predStr, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addPredStrani", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addPredStrani(@Valid @RequestBody PredmetStrani request) {

		Optional<PredmetStrani> predStr = repoStrani.findById(request.getPredmetId());
		if (predStr.isPresent()) {
			return new ResponseEntity<String>("Predmet (strani) vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoStrani.save(request);
		return new ResponseEntity<String>("Predmet (strani) uspesno dodat!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updatePredStrani", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updatePredStrani(@Valid @RequestBody PredmetStrani request) {

		repoStrani.save(request);
		return new ResponseEntity<String>("Predmet (strani) uspesno izmenjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deletePredStrani", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deletePredStrani(@Valid @RequestBody PredmetStrani request) {

		Optional<PredmetStrani> predStr = repoStrani.findById(request.getPredmetId());
		if (!predStr.isPresent()) {
			return new ResponseEntity<String>("Predmet (strani) ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoStrani.delete(request);
		return new ResponseEntity<String>("Predmet (strani) uspesno obrisan!", HttpStatus.OK);
	}

}
