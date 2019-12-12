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

import com.ftn.student.service.models.PredmetDomaci;
import com.ftn.student.service.repository.PredmetiDomaciRepository;

@RestController
public class AdminRestPredmetiDom {
	
	@Autowired
	private PredmetiDomaciRepository repoDomaci;
	
	@RequestMapping(value = "/predDomaci", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<PredmetDomaci>> predDomaci() {

		List<PredmetDomaci> predDom = repoDomaci.findAll();
		
		return new ResponseEntity<List<PredmetDomaci>>(predDom, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addPredDomaci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addPredDomaci(@Valid @RequestBody PredmetDomaci request) {

		Optional<PredmetDomaci> predDom = repoDomaci.findById(request.getPredmetId());
		if (predDom.isPresent()) {
			return new ResponseEntity<String>("Predmet (domaci) vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDomaci.save(request);
		return new ResponseEntity<String>("Predmet (domaci) uspesno dodat!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updatePredDomaci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updatePredDomaci(@Valid @RequestBody PredmetDomaci request) {

		repoDomaci.save(request);
		return new ResponseEntity<String>("Predmet (domaci) uspesno izmenjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deletePredDomaci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deletePredDomaci(@Valid @RequestBody PredmetDomaci request) {

		Optional<PredmetDomaci> predDom = repoDomaci.findById(request.getPredmetId());
		if (!predDom.isPresent()) {
			return new ResponseEntity<String>("Predmet (domaci) ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDomaci.delete(request);
		return new ResponseEntity<String>("Predmet (domaci) uspesno obrisan!", HttpStatus.OK);
	}

}