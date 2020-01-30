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
import com.ftn.student.service.models.Departman;
import com.ftn.student.service.repository.DepartmaniRepository;


@RestController
public class AdminRestDepartman {
	
	@Autowired
	private DepartmaniRepository repoDepartmani;
	
	private final Logger log = LoggerFactory.getLogger(AdminRestDepartman.class);
	
	@RequestMapping(value = "/departmani", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Departman>> departmani() {

		List<Departman> dept = repoDepartmani.findAll();
		
		return new ResponseEntity<List<Departman>>(dept, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/departmani", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addDepartman(@Valid @RequestBody Departman request) {

		Optional<Departman> dept = repoDepartmani.findById(request.getDepartmanId());
		if (dept.isPresent()) {
			log.error("Department already exists!");
			return new ResponseEntity<String>("Departman vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDepartmani.save(request);
		log.info("Department successfully inserted!");
		return new ResponseEntity<String>("Departman uspesno dodat!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/departmani/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> putDepartman(@Valid @RequestBody Departman request) {

		Optional<Departman> dept = repoDepartmani.findById(request.getDepartmanId());
		if (!dept.isPresent()) {
			log.error("Department does not exist!");
			return new ResponseEntity<String>("Departman ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDepartmani.save(request);
		log.info("Department successfully updated!");
		return new ResponseEntity<String>("Departman uspesno izmenjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/departmani/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteDepartman(@Valid @RequestBody Departman request) {

		Optional<Departman> dept = repoDepartmani.findById(request.getDepartmanId());
		if (!dept.isPresent()) {
			log.error("Department does not exist!");
			return new ResponseEntity<String>("Departman ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDepartmani.delete(request);
		log.info("Department successfully deleted!");
		return new ResponseEntity<String>("Departman uspesno obrisan!", HttpStatus.OK);
	}

}
