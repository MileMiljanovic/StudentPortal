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
import com.ftn.student.service.models.Departman;
import com.ftn.student.service.repository.DepartmaniRepository;

@RestController
public class AdminRestDepartman {
	
	@Autowired
	private DepartmaniRepository repoDepartmani;
	
	
	@RequestMapping(value = "/departmani", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Departman>> departmani() {

		List<Departman> dept = repoDepartmani.findAll();
		
		return new ResponseEntity<List<Departman>>(dept, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addDepartman", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addDepartman(@Valid @RequestBody Departman request) {

		Optional<Departman> dept = repoDepartmani.findById(request.getDepartmanId());
		if (dept.isPresent()) {
			return new ResponseEntity<String>("Departman vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDepartmani.save(request);
		return new ResponseEntity<String>("Departman uspesno dodat!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateDepartman", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updateDepartman(@Valid @RequestBody Departman request) {

		repoDepartmani.save(request);
		return new ResponseEntity<String>("Departman uspesno izmenjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteDepartman", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteDepartman(@Valid @RequestBody Departman request) {

		Optional<Departman> dept = repoDepartmani.findById(request.getDepartmanId());
		if (!dept.isPresent()) {
			return new ResponseEntity<String>("Departman ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDepartmani.delete(request);
		return new ResponseEntity<String>("Departman uspesno obrisan!", HttpStatus.OK);
	}

}
