package com.ftn.student.service.rest;

import java.util.Optional;

import javax.validation.Valid;

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

import com.ftn.student.service.emailservice.EmailService;
import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Korisnik;
import com.ftn.student.service.models.Zamena;
import com.ftn.student.service.models.ZamenaToken;
import com.ftn.student.service.repository.FormularRepository;
import com.ftn.student.service.repository.ZamenaRepository;
import com.ftn.student.service.repository.ZamenaTokenRepository;
import com.ftn.student.service.rest.requests.ConfirmationRequest;
import com.ftn.student.service.rest.requests.UserLoginRequest;

@RestController
public class UserRest {
	
	@Autowired
	private FormularRepository repoFormular;
	
	@Autowired
	private ZamenaRepository repoZamena;
	
	@Autowired
	private ZamenaTokenRepository repoZT;
	
	@Autowired
	private EmailService emailSvc;
	
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Korisnik> userLogin(@Valid @RequestBody UserLoginRequest request) {

		//toDo, login form
		
		//return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<Korisnik>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/coordinatorConfirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> coordinatorConfirm(@Valid @RequestBody ConfirmationRequest request) {

		Optional<Formular> fr = repoFormular.findById(request.getFormularId());
		
		if (!fr.isPresent()) {
			return new ResponseEntity<String>("Greska! Formular nije pronadjen!", HttpStatus.NOT_FOUND);
		}
		
		Formular f = repoFormular.findById(request.getFormularId()).get();
		f.setOdobrenjeKoord(request.getOdgovor());
		repoFormular.save(f);
		for (Zamena z: f.getZamene()) {
			emailSvc.sendEmailTeacher(z);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/headConfirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> headConfirm(@Valid @RequestBody ConfirmationRequest request) {

		Optional<Formular> fr = repoFormular.findById(request.getFormularId());
		
		if (!fr.isPresent()) {
			return new ResponseEntity<String>("Greska! Formular nije pronadjen!", HttpStatus.NOT_FOUND);
		}
		
		Formular f = repoFormular.findById(request.getFormularId()).get();		
		f.setOdobrenjeSef(request.getOdgovor());
		repoFormular.save(f);
		emailSvc.sendEmailStudent(f);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/teacherConfirm/{uuid}/{zamena}/{odgovor}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> teacherConfirm(@PathVariable String uuid, 
			@PathVariable String zamena, @PathVariable String odgovor) {
		
		Optional<ZamenaToken> ztk = repoZT.findById(zamena);
		
		if (!ztk.isPresent()) {
			return new ResponseEntity<String>("Nevalidan token!", HttpStatus.BAD_REQUEST);
		}
		
		ZamenaToken zt = ztk.get();
		
		if (!zt.getToken().equals(uuid)) {
			return new ResponseEntity<String>("Nevalidan token!", HttpStatus.BAD_REQUEST);
		}
				
		Optional<Zamena> z = repoZamena.findById(zamena);
		if (!z.isPresent()) {
			return new ResponseEntity<String>("Zamena nije pronadjena!", HttpStatus.NOT_FOUND);
		}
		if (!odgovor.equals("Y") && !odgovor.equals("N")) {
			return new ResponseEntity<String>("Nevalidan odgovor!", HttpStatus.BAD_REQUEST);
		}
		
		Zamena zam = z.get();
		if (zam.getOdobreno() != null) {
			return new ResponseEntity<String>("Vec ste odgovorili!", HttpStatus.BAD_REQUEST);
		}
		zam.setOdobreno(odgovor);
		repoZamena.save(zam);

		return new ResponseEntity<String>("Uspesno odobreno!", HttpStatus.OK);
	}

}
