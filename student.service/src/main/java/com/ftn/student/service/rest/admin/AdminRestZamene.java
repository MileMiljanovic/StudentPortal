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
	public @ResponseBody ResponseEntity<String> addZamena(@Valid @RequestBody Zamena request) {

		Optional<Zamena> zam = repoZ.findById(request.getIdzamena());
		if (zam.isPresent()) {
			log.error("Substitute already exists!");
			return new ResponseEntity<String>("Zamena vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoZ.save(request);
		log.info("Substitute successfully inserted!");
		return new ResponseEntity<String>("Zamena uspesno dodata!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zamena/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> putZamena(@Valid @RequestBody Zamena request) {

		Optional<Zamena> zam = repoZ.findById(request.getIdzamena());
		if (!zam.isPresent()) {
			log.error("Substitute does not exist!");
			return new ResponseEntity<String>("Zamena ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoZ.save(request);
		log.info("Substitute successfully updated!");
		return new ResponseEntity<String>("Zamena uspesno izmenjena!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zamena/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteZamena(@Valid @RequestBody Zamena request) {

		Optional<Zamena> zam = repoZ.findById(request.getIdzamena());
		if (!zam.isPresent()) {
			log.error("Substitute does not exist!");
			return new ResponseEntity<String>("Zamena ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoZ.delete(request);
		log.info("Substitute successfully deleted!");
		return new ResponseEntity<String>("Zamena uspesno obrisana!", HttpStatus.OK);
	}

}
