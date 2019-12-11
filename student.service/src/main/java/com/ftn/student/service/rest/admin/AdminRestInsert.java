package com.ftn.student.service.rest.admin;

import java.util.Optional;
import java.util.UUID;

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
import com.ftn.student.service.models.Zamena;
import com.ftn.student.service.models.ZamenaToken;
import com.ftn.student.service.repository.DepartmaniRepository;
import com.ftn.student.service.repository.FormularRepository;
import com.ftn.student.service.repository.KorisniciRepository;
import com.ftn.student.service.repository.NastavnikRepository;
import com.ftn.student.service.repository.PredmetiDomaciRepository;
import com.ftn.student.service.repository.PredmetiStraniRepository;
import com.ftn.student.service.repository.SProgramDomaciRepository;
import com.ftn.student.service.repository.SProgramStraniRepository;
import com.ftn.student.service.repository.StudentRepository;
import com.ftn.student.service.repository.ZamenaRepository;
import com.ftn.student.service.rest.requests.SubmitFormRequest;
import com.ftn.student.service.rest.requestsadmin.DepartmanRequest;

@RestController
public class AdminRestInsert {
	
	@Autowired
	private KorisniciRepository repoKorisnici;
	
	@Autowired
	private DepartmaniRepository repoDepartmani;
	
	@Autowired
	private SProgramDomaciRepository repoDomaci;
	
	@Autowired
	private SProgramStraniRepository repoStrani;
	
	@Autowired
	private StudentRepository repoStudent;
	
	@Autowired
	private PredmetiDomaciRepository repoPD;
	
	@Autowired
	private PredmetiStraniRepository repoPS;
	
	@Autowired
	private FormularRepository repoF;
	
	@Autowired
	private ZamenaRepository repoZ;
	
	@Autowired
	private NastavnikRepository repoNast;
	
	@RequestMapping(value = "/addDepartman", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> submitForm(@Valid @RequestBody DepartmanRequest request) {

		Optional<Departman> dept = repoDepartmani.findById(request.getDepartman().getDepartmanId());
		if (dept.isPresent()) {
			return new ResponseEntity<String>("Departman vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDepartmani.save(request.getDepartman());
		return new ResponseEntity<String>("Departman uspesno dodat!", HttpStatus.OK);
	}

}
