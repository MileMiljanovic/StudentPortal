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
import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Sequence;
import com.ftn.student.service.repository.FormularRepository;
import com.ftn.student.service.repository.SequenceRepository;

@RestController
public class AdminRestFormular {
	
	@Autowired
	private FormularRepository repoFormular;
	
	@Autowired
	private SequenceRepository repoSequence;
	
	private final Logger log = LoggerFactory.getLogger(AdminRestFormular.class);
	
	@RequestMapping(value = "/formular", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Formular>> formulari() {

		List<Formular> form = repoFormular.findAll();
		return new ResponseEntity<List<Formular>>(form, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/formular/getId", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<JsonResponse> fetchId() {

		Sequence s = new Sequence(null);
		repoSequence.save(s);
		return new ResponseEntity<JsonResponse>(new JsonResponse("F" + s.getCounter().toString()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/formular", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JsonResponse> addFormular(@Valid @RequestBody Formular request) {

		Optional<Formular> form = repoFormular.findById(request.getIdformular());
		if (form.isPresent()) {
			log.error("Formular already exists!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Formular vec postoji!"), HttpStatus.BAD_REQUEST);
		}
		
		List<Formular> fs = repoFormular.findByStudent(request.getStudent());
		if (!fs.isEmpty()) {
			return new ResponseEntity<JsonResponse>(new JsonResponse("Student vec ima formular!"), HttpStatus.BAD_REQUEST);
		}
		repoFormular.save(request);
		log.info("Formular successfully inserted!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Formular uspesno dodat!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/formular/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JsonResponse> putFormular(@Valid @RequestBody Formular request) {

		Optional<Formular> form = repoFormular.findById(request.getIdformular());
		if (!form.isPresent()) {
			log.error("Formular does not exist!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Formular ne postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoFormular.save(request);
		log.info("Formular successfully updated!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Formular uspesno izmenjen!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/formular/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<JsonResponse> deleteFormular(@PathVariable String id) {

		Optional<Formular> form = repoFormular.findById(id);
		if (!form.isPresent()) {
			log.error("Formular does not exist!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Formular ne postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoFormular.delete(form.get());
		log.info("Formular successfully deleted!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Formular uspesno obrisan!"), HttpStatus.OK);
	}

}
