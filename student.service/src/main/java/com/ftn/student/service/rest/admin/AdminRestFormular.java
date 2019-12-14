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
import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Student;
import com.ftn.student.service.models.StudijskiProgramStrani;
import com.ftn.student.service.repository.FormularRepository;
import com.ftn.student.service.repository.SProgramStraniRepository;
import com.ftn.student.service.repository.StudentRepository;
import com.ftn.student.service.rest.responsesadmin.AdminFormularResponse;

@RestController
public class AdminRestFormular {
	
	@Autowired
	private FormularRepository repoFormular;
	
	@Autowired 
	private StudentRepository repoStudent;
	
	@Autowired
	private SProgramStraniRepository repoStrani;
	
	private final Logger log = LoggerFactory.getLogger(AdminRestFormular.class);
	
	@RequestMapping(value = "/formulari", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<AdminFormularResponse> formulari() {

		List<Formular> form = repoFormular.findAll();
		List<Student> studenti = repoStudent.findAll();
		List<StudijskiProgramStrani> strani = repoStrani.findAll();
		
		AdminFormularResponse response = new AdminFormularResponse(form, studenti, strani);
		return new ResponseEntity<AdminFormularResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addFormular", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addFormular(@Valid @RequestBody Formular request) {

		Optional<Formular> form = repoFormular.findById(request.getIdformular());
		if (form.isPresent()) {
			log.error("Formular already exists!");
			return new ResponseEntity<String>("Formular vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoFormular.save(request);
		log.info("Formular successfully inserted!");
		return new ResponseEntity<String>("Formular uspesno dodat!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateFormular", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updateFormular(@Valid @RequestBody Formular request) {

		Optional<Formular> form = repoFormular.findById(request.getIdformular());
		if (!form.isPresent()) {
			log.error("Formular does not exist!");
			return new ResponseEntity<String>("Formular ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoFormular.save(request);
		log.info("Formular successfully updated!");
		return new ResponseEntity<String>("Formular uspesno izmenjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteFormular", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteFormular(@Valid @RequestBody Formular request) {

		Optional<Formular> form = repoFormular.findById(request.getIdformular());
		if (!form.isPresent()) {
			log.error("Formular does not exist!");
			return new ResponseEntity<String>("Formular ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoFormular.delete(request);
		log.info("Formular successfully deleted!");
		return new ResponseEntity<String>("Formular uspesno obrisan!", HttpStatus.OK);
	}

}
