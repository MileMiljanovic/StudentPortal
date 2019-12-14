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
import com.ftn.student.service.models.Student;
import com.ftn.student.service.models.StudijskiProgramDomaci;
import com.ftn.student.service.repository.SProgramDomaciRepository;
import com.ftn.student.service.repository.StudentRepository;
import com.ftn.student.service.rest.responsesadmin.AdminStudentResponse;

@RestController
public class AdminRestStudenti {
	
	@Autowired
	private StudentRepository repoStudent;
	
	@Autowired
	private SProgramDomaciRepository repoDomaci;
	
	@RequestMapping(value = "/studenti", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<AdminStudentResponse> studenti() {

		List<Student> stud = repoStudent.findAll();
		List<StudijskiProgramDomaci> progDom = repoDomaci.findAll();
		
		AdminStudentResponse response = new AdminStudentResponse(stud, progDom);
		
		return new ResponseEntity<AdminStudentResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addStudent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addStudent(@Valid @RequestBody Student request) {

		Optional<Student> stud = repoStudent.findById(request.getBrindeksa());
		if (stud.isPresent()) {
			return new ResponseEntity<String>("Student vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoStudent.save(request);
		return new ResponseEntity<String>("Student uspesno dodat!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateStudent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updateStudent(@Valid @RequestBody Student request) {

		repoStudent.save(request);
		return new ResponseEntity<String>("Student uspesno izmenjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteStudent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteStudent(@Valid @RequestBody Student request) {

		Optional<Student> stud = repoStudent.findById(request.getBrindeksa());
		if (!stud.isPresent()) {
			return new ResponseEntity<String>("Student ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoStudent.delete(request);
		return new ResponseEntity<String>("Student uspesno obrisan!", HttpStatus.OK);
	}

}
