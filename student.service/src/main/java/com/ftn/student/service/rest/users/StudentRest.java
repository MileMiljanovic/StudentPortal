package com.ftn.student.service.rest.users;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.kie.api.runtime.KieSession;
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
import com.ftn.student.service.models.PredmetDomaci;
import com.ftn.student.service.models.PredmetStrani;
import com.ftn.student.service.models.Sequence;
import com.ftn.student.service.models.Student;
import com.ftn.student.service.models.StudijskiProgramStrani;
import com.ftn.student.service.models.Zamena;
import com.ftn.student.service.repository.FormularRepository;
import com.ftn.student.service.repository.PredmetiDomaciRepository;
import com.ftn.student.service.repository.PredmetiStraniRepository;
import com.ftn.student.service.repository.SProgramStraniRepository;
import com.ftn.student.service.repository.SequenceRepository;
import com.ftn.student.service.repository.StudentRepository;
import com.ftn.student.service.repository.ZamenaRepository;
import com.ftn.student.service.rest.requests.CancelFormRequest;
import com.ftn.student.service.rest.requests.ConfirmProgramRequest;
import com.ftn.student.service.rest.requests.StudentLoginRequest;
import com.ftn.student.service.rest.requests.SubmitFormRequest;
import com.ftn.student.service.rest.responses.ConfirmProgramResponse;
import com.ftn.student.service.rest.responses.StudentLoginResponse;

@RestController
public class StudentRest {
	
	@Autowired
	private StudentRepository repoStudent;
	
	@Autowired
	private PredmetiDomaciRepository repoPredmetDomaci;
	
	@Autowired
	private PredmetiStraniRepository repoPredmetStrani;
	
	@Autowired
	private SProgramStraniRepository repoStrani;
	
	@Autowired
	private FormularRepository repoFormular;
	
	@Autowired
	private ZamenaRepository repoZamena;
	
	@Autowired
	private SequenceRepository repoSequence;
	
	@Autowired
	private KieSession kieSession;
	
	private final Logger log = LoggerFactory.getLogger(StudentRest.class);
	
	@RequestMapping(value = "/studentLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StudentLoginResponse> studentLogin(@Valid @RequestBody StudentLoginRequest request) {

		Optional<Student> st = repoStudent.findById(request.getBrIndeksa());
		if (!st.isPresent()) {
			return new ResponseEntity<StudentLoginResponse>(HttpStatus.NOT_FOUND);
		}
		Student s = st.get();
		List<StudijskiProgramStrani> programi = repoStrani.findAll();
		List<Formular> f = repoFormular.findByStudent(s);
		if (!f.isEmpty()) {
			StudentLoginResponse resp = new StudentLoginResponse(s, programi, f.get(0));
			log.info("Student already has a formular!");
			return new ResponseEntity<StudentLoginResponse>(resp, HttpStatus.ALREADY_REPORTED);
		}
		
		StudentLoginResponse response = new StudentLoginResponse(s, programi, null);
		log.info("Student login successful!");
		return new ResponseEntity<StudentLoginResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirmProgram", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ConfirmProgramResponse> confirmProgram(@Valid @RequestBody ConfirmProgramRequest request) {
		
		List<PredmetDomaci> domaci = repoPredmetDomaci.findByProgram(request.getStudent().getStudije());
		List<PredmetStrani> strani = repoPredmetStrani.findByProgramStrani(request.getProgramStrani());
		Sequence s = new Sequence(null);
		repoSequence.save(s);
		Formular f = new Formular("F" + s.getCounter().toString(), request.getStudent(), request.getProgramStrani(), null, null, new Timestamp(System.currentTimeMillis()), null);
		repoFormular.save(f);
		ConfirmProgramResponse response = new ConfirmProgramResponse(request.getStudent(), request.getProgramStrani(), domaci, strani, f.getIdformular());
		log.info("Foreign program successfully chosen!");
		return new ResponseEntity<ConfirmProgramResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/submitForm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> submitForm(@Valid @RequestBody SubmitFormRequest request) {

		kieSession.insert(request);
		kieSession.fireAllRules(); 
		
		for (Zamena z: request.getZamene()) {
			UUID uuid = UUID.randomUUID();
			z.setToken(uuid.toString());
			repoZamena.save(z);
		}	
		
		log.info("Formular successfully submitted!");
		return new ResponseEntity<String>("Formular uspesno prosledjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cancelForm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> cancelForm(@Valid @RequestBody CancelFormRequest request) {

		Optional<Formular> fr = repoFormular.findById(request.getFormularId());
		
		if (!fr.isPresent()) {
			log.error("Formular not found!");
			return new ResponseEntity<String>("Greska! Formular nije pronadjen!", HttpStatus.NOT_FOUND);
		}
		
		Formular f = repoFormular.findById(request.getFormularId()).get();
		repoFormular.delete(f);
		log.info("Formular successfully cancelled!");
		return new ResponseEntity<String>("Formular uspesno obrisan!", HttpStatus.OK);
	}

}
