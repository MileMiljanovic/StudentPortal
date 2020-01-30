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
import com.ftn.student.service.models.Zamena;
import com.ftn.student.service.repository.ZamenaRepository;

@RestController
public class AdminRestZamene {
	
	@Autowired
	private ZamenaRepository repoZ;
	
	private final Logger log = LoggerFactory.getLogger(AdminRestZamene.class);
	
	@RequestMapping(value = "/zamena", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Zamena>> zamene() {

		List<Zamena> zam = repoZ.findAll();
		
		return new ResponseEntity<List<Zamena>>(zam, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zamena", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JsonResponse> addZamena(@Valid @RequestBody Zamena request) {

		Optional<Zamena> zam = repoZ.findById(request.getIdzamena());
		if (zam.isPresent()) {
			log.error("Substitute already exists!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Zamena vec postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoZ.save(request);
		log.info("Substitute successfully inserted!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Zamena uspesno dodata!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zamena/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JsonResponse> putZamena(@Valid @RequestBody Zamena request) {

		Optional<Zamena> zam = repoZ.findById(request.getIdzamena());
		if (!zam.isPresent()) {
			log.error("Substitute does not exist!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Zamena ne postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoZ.save(request);
		log.info("Substitute successfully updated!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Zamena uspesno izmenjena!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zamena/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<JsonResponse> deleteZamena(@PathVariable String id) {

		Optional<Zamena> zam = repoZ.findById(id);
		if (!zam.isPresent()) {
			log.error("Substitute does not exist!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Zamena ne postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoZ.delete(zam.get());
		log.info("Substitute successfully deleted!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Zamena uspesno obrisana!"), HttpStatus.OK);
	}

}
