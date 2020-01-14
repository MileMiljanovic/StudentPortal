package com.ftn.student.service.rest.users;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.drools.core.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
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
public class PodlogaController {
	
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
	
	private final Logger log = LoggerFactory.getLogger(PodlogaController.class);
	
	@RequestMapping(value = "/indexValidation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Student> studentLogin(@Valid @RequestBody StudentLoginRequest request) {

		Optional<Student> st = repoStudent.findById(request.getBrIndeksa());
		if (!st.isPresent()) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		Student s = st.get();
		
		log.info("Student login successful!");
		return new ResponseEntity<Student>(s, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/podloga", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<StudentLoginResponse> initFormular(@Valid @RequestBody Student request) {
			
		List<Formular> f = repoFormular.findByStudent(request);
		if (!f.isEmpty()) {
			StudentLoginResponse resp = new StudentLoginResponse(request, null, f.get(0));
			log.info("Student already has a formular!");
			return new ResponseEntity<StudentLoginResponse>(resp, HttpStatus.ALREADY_REPORTED);
		}
		List<StudijskiProgramStrani> programi = repoStrani.findAll();
		StudentLoginResponse response = new StudentLoginResponse(request, programi, null);
		log.info("Formular creation started!");
		return new ResponseEntity<StudentLoginResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/podloga/straniProgram", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ConfirmProgramResponse> confirmProgram(@Valid @RequestBody ConfirmProgramRequest request) {
		
		List<PredmetDomaci> domaci = repoPredmetDomaci.findByProgram(request.getStudent().getStudije());
		List<PredmetStrani> strani = repoPredmetStrani.findByProgramStrani(request.getProgramStrani());
		Sequence s = new Sequence(null);
		repoSequence.save(s);
		ConfirmProgramResponse response = new ConfirmProgramResponse(request.getStudent(), request.getProgramStrani(), domaci, strani, "F" + s.getCounter().toString());
		log.info("Foreign program successfully chosen!");
		return new ResponseEntity<ConfirmProgramResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/podloga/{id}/zamene", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> submitForm(@PathVariable String id, @Valid @RequestBody SubmitFormRequest request) {
		
		Formular fr = new Formular(request.getFormularId(), request.getStudent(), request.getProgramStrani(), null, null, new Timestamp(System.currentTimeMillis()), null, false);
		
		kieSession.insert(request);
		kieSession.insert(fr);
		
		kieSession.getAgenda().getAgendaGroup("valid").setFocus();		
		kieSession.fireAllRules(); 
		
		@SuppressWarnings("unchecked")
		Collection<Formular> formular = (Collection<Formular>) kieSession.getObjects(new ClassObjectFilter(Formular.class));
		
		Formular f = formular.iterator().next();

		if (f.isValid()) {
			
			log.info("Formular valid and successfully submitted!");
			
			repoFormular.save(f);
			
			for (Zamena z: request.getZamene()) {
				UUID uuid = UUID.randomUUID();
				z.setToken(uuid.toString());
				repoZamena.save(z);
			}	
			
			kieSession.getAgenda().getAgendaGroup("clear").setFocus();		
			kieSession.fireAllRules(); 
			return new ResponseEntity<String>("Formular validan i uspesno prosledjen!", HttpStatus.OK);
		}
		else {
			
			log.error("Formular is not valid!");
			kieSession.getAgenda().getAgendaGroup("clear").setFocus();		
			kieSession.fireAllRules(); 
			return new ResponseEntity<String>("Greska! Formular nije validan! Proverite ESPB bodove.", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/api/podloga/{id}/cancelForm", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
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
