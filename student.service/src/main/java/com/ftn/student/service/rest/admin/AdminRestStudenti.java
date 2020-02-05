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
import com.ftn.student.service.models.Student;
import com.ftn.student.service.repository.FormularRepository;
import com.ftn.student.service.repository.StudentRepository;

@RestController
public class AdminRestStudenti {
	
	@Autowired
	private StudentRepository repoStudent;
	
	@Autowired
	private FormularRepository repoFormular;
	
	private final Logger log = LoggerFactory.getLogger(AdminRestStudenti.class);
	
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Student>> studenti() {

		List<Student> stud = repoStudent.findAll();
		
		return new ResponseEntity<List<Student>>(stud, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JsonResponse> addStudent(@Valid @RequestBody Student request) {

		Optional<Student> stud = repoStudent.findById(request.getBrindeksa());
		if (stud.isPresent()) {
			log.error("Student already exists!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Student vec postoji!"), HttpStatus.BAD_REQUEST);
		}
		repoStudent.save(request);
		log.info("Student successfully inserted!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Student uspesno dodat!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/student/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JsonResponse> putStudent(@Valid @RequestBody Student request) {

		Optional<Student> stud = repoStudent.findById(request.getBrindeksa());
		if (!stud.isPresent()) {
			log.error("Student does not exist!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Student ne postoji!"), HttpStatus.BAD_REQUEST);
		}
		
		/*if(!request.getStudije().getNaziv().equals(stud.get().getStudije().getNaziv())) {
			List<Formular> forms = repoFormular.findByStudent(stud.get());
			for (Formular f: forms) {
				repoFormular.delete(f);
			}
		}*/
		repoStudent.save(request);
		log.info("Student successfully updated!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Student uspesno izmenjen!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<JsonResponse> deleteStudent(@PathVariable String id) {

		Optional<Student> stud = repoStudent.findById(id);
		if (!stud.isPresent()) {
			log.error("Student does not exist!");
			return new ResponseEntity<JsonResponse>(new JsonResponse("Student ne postoji!"), HttpStatus.BAD_REQUEST);
		}
		
		List<Formular> forms = repoFormular.findByStudent(stud.get());
		for (Formular f: forms) {
			repoFormular.delete(f);
		}
		
		repoStudent.delete(stud.get());
		log.info("Student successfully deleted!");
		return new ResponseEntity<JsonResponse>(new JsonResponse("Student uspesno obrisan!"), HttpStatus.OK);
	}

}
