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

import com.ftn.student.service.models.Nastavnik;
import com.ftn.student.service.models.PredmetDomaci;
import com.ftn.student.service.models.StudijskiProgramDomaci;
import com.ftn.student.service.repository.NastavnikRepository;
import com.ftn.student.service.repository.PredmetiDomaciRepository;
import com.ftn.student.service.repository.SProgramDomaciRepository;
import com.ftn.student.service.rest.responsesadmin.AdminPredmetDResponse;

@RestController
public class AdminRestPredmetiDom {
	
	@Autowired
	private PredmetiDomaciRepository repoDomaci;
	
	@Autowired
	private SProgramDomaciRepository repoProgrami;
	
	@Autowired
	private NastavnikRepository repoNastavnik;
	
	private final Logger log = LoggerFactory.getLogger(AdminRestPredmetiDom.class);
	
	@RequestMapping(value = "/predDomaci", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<AdminPredmetDResponse> predDomaci() {

		List<PredmetDomaci> predDom = repoDomaci.findAll();
		List<StudijskiProgramDomaci> prog = repoProgrami.findAll();
		List<Nastavnik> nast = repoNastavnik.findAll();
		
		AdminPredmetDResponse response = new AdminPredmetDResponse(predDom, prog, nast);
		
		return new ResponseEntity<AdminPredmetDResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addPredDomaci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addPredDomaci(@Valid @RequestBody PredmetDomaci request) {

		Optional<PredmetDomaci> predDom = repoDomaci.findById(request.getPredmetId());
		if (predDom.isPresent()) {
			log.error("Subject already exists!");
			return new ResponseEntity<String>("Predmet (domaci) vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDomaci.save(request);
		log.info("Subject successfully inserted!");
		return new ResponseEntity<String>("Predmet (domaci) uspesno dodat!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updatePredDomaci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updatePredDomaci(@Valid @RequestBody PredmetDomaci request) {

		Optional<PredmetDomaci> predDom = repoDomaci.findById(request.getPredmetId());
		if (!predDom.isPresent()) {
			log.error("Subject does not exist!");
			return new ResponseEntity<String>("Predmet (domaci) ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDomaci.save(request);
		log.info("Subject successfully updated!");
		return new ResponseEntity<String>("Predmet (domaci) uspesno izmenjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deletePredDomaci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deletePredDomaci(@Valid @RequestBody PredmetDomaci request) {

		Optional<PredmetDomaci> predDom = repoDomaci.findById(request.getPredmetId());
		if (!predDom.isPresent()) {
			log.error("Subject does not exist!");
			return new ResponseEntity<String>("Predmet (domaci) ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDomaci.delete(request);
		log.info("Subject successfully deleted!");
		return new ResponseEntity<String>("Predmet (domaci) uspesno obrisan!", HttpStatus.OK);
	}

}
