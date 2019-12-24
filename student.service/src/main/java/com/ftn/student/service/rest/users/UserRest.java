package com.ftn.student.service.rest.users;

import java.io.IOException;
import java.util.Optional;
import javax.mail.MessagingException;
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
import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Korisnik;
import com.ftn.student.service.models.Uloga;
import com.ftn.student.service.models.Zamena;
import com.ftn.student.service.repository.FormularRepository;
import com.ftn.student.service.repository.KorisniciRepository;
import com.ftn.student.service.repository.ZamenaRepository;
import com.ftn.student.service.rest.requests.ConfirmationRequest;
import com.ftn.student.service.rest.requests.UserLoginRequest;
import com.ftn.student.service.rest.responses.UserLoginResponse;
import com.ftn.student.service.utils.EmailService;
import com.ftn.student.service.utils.FormularDBUtils;
import com.itextpdf.text.DocumentException;

@RestController
public class UserRest {
	
	@Autowired
	private KorisniciRepository repoKorisnici;
	
	@Autowired
	private FormularRepository repoFormular;
	
	@Autowired
	private ZamenaRepository repoZamena;
	
	@Autowired
	private EmailService emailSvc;
	
	private final Logger log = LoggerFactory.getLogger(UserRest.class);
	
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<UserLoginResponse> userLogin(@Valid @RequestBody UserLoginRequest request) {

		Optional<Korisnik> kor = repoKorisnici.findById(request.getUsername());
		if (!kor.isPresent()) {
			log.error("Login unsuccessful! Invalid user!");
			return new ResponseEntity<UserLoginResponse>(HttpStatus.UNAUTHORIZED);
		}
		
		Korisnik k = kor.get();
		if (!k.getPassword().equals(request.getPassword())) {
			log.error("Login unsuccessful! Invalid password!");
			return new ResponseEntity<UserLoginResponse>(HttpStatus.UNAUTHORIZED);
		}
		
		
		if (k.getUloga().equals(Uloga.ADMIN)) {
			UserLoginResponse response = new UserLoginResponse();
			response.setKorisnik(k);
			response.setFormulari(null);
			log.info("Successfully logged in as: " + k.getUsername());
			return new ResponseEntity<UserLoginResponse>(response, HttpStatus.OK);
		}
		
		else if (k.getUloga().equals(Uloga.KOORDINATOR)) {
			UserLoginResponse response = FormularDBUtils.koordFormulariResponse(k, repoFormular);
			log.info("Successfully logged in as: " + k.getUsername());
			return new ResponseEntity<UserLoginResponse>(response,  HttpStatus.OK);
		}
		
		else {
			UserLoginResponse response = FormularDBUtils.sefFormulariResponse(k, repoFormular);
			log.info("Successfully logged in as: " + k.getUsername());
			return new ResponseEntity<UserLoginResponse>(response,  HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/api/koordinator/koordinatorConfirm", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> coordinatorConfirm(@Valid @RequestBody ConfirmationRequest request) throws MessagingException {

		Formular f = request.getFormularId();
		f.setOdobrenjeKoord(request.getOdgovor());
		repoFormular.save(f);
		for (Zamena z: f.getZamene()) {
			emailSvc.sendEmailTeacher(z, f);
		}
		log.info("Successfully confirmed a formular! ID: " + f.getIdformular());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/sef/sefConfirm", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> headConfirm(@Valid @RequestBody ConfirmationRequest request) throws MessagingException, IOException, DocumentException {
	
		Formular f = request.getFormularId();
		f.setOdobrenjeSef(request.getOdgovor());
		repoFormular.save(f);
		emailSvc.sendEmailStudent(f);
		log.info("Successfully confirmed a formular! ID: " + f.getIdformular());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/nastavnik/{uuid}/{zamena}/{odgovor}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<String> teacherConfirm(@PathVariable String uuid, 
			@PathVariable String zamena, @PathVariable String odgovor) {
		
		Optional<Zamena> z = repoZamena.findById(zamena);
		
		if (!z.isPresent()) {
			log.error("Substitute does not exist!");
			return new ResponseEntity<String>("Zamena nije pronadjena!", HttpStatus.NOT_FOUND);
		}
		
		if (!odgovor.equals("Y") && !odgovor.equals("N")) {
			log.error("Invalid response!");
			return new ResponseEntity<String>("Nevalidan odgovor!", HttpStatus.BAD_REQUEST);
		}
		
		Zamena zam = z.get();
		
		if (!zam.getToken().equals(uuid)) {
			log.error("Invalid token!");
			return new ResponseEntity<String>("Nevalidan token!", HttpStatus.BAD_REQUEST);
		}
				
		if (zam.getOdobreno() != null) {
			log.error("Already answered!");
			return new ResponseEntity<String>("Vec ste odgovorili!", HttpStatus.BAD_REQUEST);
		}
		zam.setOdobreno(odgovor);
		repoZamena.save(zam);
		log.info("Successfully confirmed a substitute! ID: " + zam.getIdzamena());
		return new ResponseEntity<String>("Uspesno odobreno!", HttpStatus.OK);
	}

}
