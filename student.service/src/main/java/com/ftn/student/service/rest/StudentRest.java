package com.ftn.student.service.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.student.service.models.PredmetDomaci;
import com.ftn.student.service.models.Student;
import com.ftn.student.service.repository.PredmetiDomaciRepository;
import com.ftn.student.service.repository.StudentRepository;
import com.ftn.student.service.rest.requests.StudentLoginRequest;
import com.ftn.student.service.rest.responses.StudentLoginResponse;

@RestController
public class StudentRest {
	
	@Autowired
	private StudentRepository repoStudent;
	
	@Autowired
	private PredmetiDomaciRepository repoDomaci;
	
	@RequestMapping(value = "/studentLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StudentLoginResponse> studentLogin(@Valid @RequestBody StudentLoginRequest request) {

		Optional<Student> st = repoStudent.findById(request.getBrIndeksa());
		if (!st.isPresent()) {
			return new ResponseEntity<StudentLoginResponse>(HttpStatus.NOT_FOUND);
		}
		Student s = st.get();
		List<PredmetDomaci> predmeti = repoDomaci.findByProgram(s.getStudije());
		StudentLoginResponse response = new StudentLoginResponse(s, predmeti);
		return new ResponseEntity<StudentLoginResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/submitForm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> submitForm(HttpEntity<String> httpEntity) {

		System.out.println("SUBMIT FORM");
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cancelForm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> cancelForm(HttpEntity<String> httpEntity) {

		System.out.println("CANCEL FORM");
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
