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

import com.ftn.student.service.models.PredmetStrani;
import com.ftn.student.service.models.StudijskiProgramStrani;
import com.ftn.student.service.repository.PredmetiStraniRepository;
import com.ftn.student.service.repository.SProgramStraniRepository;
import com.ftn.student.service.rest.responsesadmin.AdminPredmetSResponse;

@RestController
public class AdminRestPredmetiStr {
	
	@Autowired
	private PredmetiStraniRepository repoStrani;
	
	@Autowired
	private SProgramStraniRepository repoProgrami;
	
	private final Logger log = LoggerFactory.getLogger(AdminRestPredmetiStr.class);
	
	@RequestMapping(value = "/predStrani", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<AdminPredmetSResponse> predStrani() {

		List<PredmetStrani> predStr = repoStrani.findAll();
		List<StudijskiProgramStrani> prog = repoProgrami.findAll();
		
		AdminPredmetSResponse response = new AdminPredmetSResponse(predStr, prog);
		
		return new ResponseEntity<AdminPredmetSResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/predStrani/addPredStrani", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addPredStrani(@Valid @RequestBody PredmetStrani request) {

		Optional<PredmetStrani> predStr = repoStrani.findById(request.getPredmetId());
		if (predStr.isPresent()) {
			log.error("Subject already exists!");
			return new ResponseEntity<String>("Predmet (strani) vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoStrani.save(request);
		log.info("Subject successfully inserted!");
		return new ResponseEntity<String>("Predmet (strani) uspesno dodat!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/predStrani/updatePredStrani", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updatePredStrani(@Valid @RequestBody PredmetStrani request) {

		Optional<PredmetStrani> predStr = repoStrani.findById(request.getPredmetId());
		if (!predStr.isPresent()) {
			log.error("Subject does not exist!");
			return new ResponseEntity<String>("Predmet (strani) ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoStrani.save(request);
		log.info("Subject successfully updated!");
		return new ResponseEntity<String>("Predmet (strani) uspesno izmenjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/predStrani/deletePredStrani", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deletePredStrani(@Valid @RequestBody PredmetStrani request) {

		Optional<PredmetStrani> predStr = repoStrani.findById(request.getPredmetId());
		if (!predStr.isPresent()) {
			log.error("Subject does not exist!");
			return new ResponseEntity<String>("Predmet (strani) ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoStrani.delete(request);
		log.info("Subject successfully deleted!");
		return new ResponseEntity<String>("Predmet (strani) uspesno obrisan!", HttpStatus.OK);
	}

}
