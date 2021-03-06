package com.ftn.student.service.rest.users;

import java.io.IOException;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
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
public class ConfirmationController {
	
	@Autowired
	private KorisniciRepository repoKorisnici;
	
	@Autowired
	private FormularRepository repoFormular;
	
	@Autowired
	private ZamenaRepository repoZamena;
	
	@Autowired
	private EmailService emailSvc;
	
	private final Logger log = LoggerFactory.getLogger(ConfirmationController.class);
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Korisnik> userLogin(@Valid @RequestBody UserLoginRequest request, HttpSession session) {

		Korisnik k = repoKorisnici.findById(request.getUsername()).get();
		log.info("Successfully logged in as: " + request.getUsername());
		return new ResponseEntity<Korisnik>(k, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/api/formulari", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<UserLoginResponse> userLogged(@Valid @RequestBody Korisnik request) { 
		
		if (request.getUloga().equals(Uloga.KOORDINATOR)) {
			log.info("Fetching a list of formulars...");
			UserLoginResponse response = FormularDBUtils.koordFormulariResponse(request, repoFormular);
			return new ResponseEntity<UserLoginResponse>(response,  HttpStatus.OK);
		}
		
		else if (request.getUloga().equals(Uloga.SEF)) {
			log.info("Fetching a list of formulars...");
			UserLoginResponse response = FormularDBUtils.sefFormulariResponse(request, repoFormular);
			return new ResponseEntity<UserLoginResponse>(response,  HttpStatus.OK);
		}
		
		else {
			log.info("Invalid role for this operation!");
			return new ResponseEntity<UserLoginResponse>(HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@RequestMapping(value = "/api/formulari/{id}/koordinatorConfirm", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> coordinatorConfirm(@PathVariable String id, @Valid @RequestBody ConfirmationRequest request) throws MessagingException, IOException, DocumentException {

		Formular f = request.getFormularId();
		f.setOdobrenjeKoord(request.getOdgovor());
		if (request.getOdgovor().equals("Y")) {
			for (Zamena z: f.getZamene()) {
				emailSvc.sendEmailTeacher(z, f);
			}
			emailSvc.sendEmailStudentConfirm(f);
		}
		else {
			emailSvc.sendEmailStudentReject(f, "KOORDINATOR");
		}
		repoFormular.save(f);
		log.info("Successfully confirmed a formular! ID: " + f.getIdformular());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/formulari/{id}/sefConfirm", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> headConfirm(@PathVariable String id, @Valid @RequestBody ConfirmationRequest request) throws MessagingException, IOException, DocumentException {
	
		Formular f = request.getFormularId();
		f.setOdobrenjeSef(request.getOdgovor());
		repoFormular.save(f);
		if (request.getOdgovor().equals("Y")) {
			emailSvc.sendEmailStudent(f);
		}
		else {
			emailSvc.sendEmailStudentReject(f, "SEF");
		}
		log.info("Successfully confirmed a formular! ID: " + f.getIdformular());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/formulari/{formular}/zamene/{zamena}/{uuid}/{odgovor}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> teacherConfirm(@PathVariable String formular, @PathVariable String zamena, 
			@PathVariable String uuid, @PathVariable String odgovor) throws MessagingException, IOException, DocumentException {
		
		Optional<Zamena> z = repoZamena.findById(zamena);
		Optional<Formular> f = repoFormular.findById(formular);
		
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
			if (zam.getOdobreno().equals(odgovor)) {
				log.error("Already answered with that response!");
				return new ResponseEntity<String>("Vec ste dali taj odgovor!", HttpStatus.BAD_REQUEST);
			}
		}

		zam.setOdobreno(odgovor);
		repoZamena.save(zam);
		emailSvc.sendEmailStudentTeacher(f.get(), zam, odgovor);
		log.info("Successfully confirmed a substitute! ID: " + zam.getIdzamena());
		return new ResponseEntity<String>("Uspesno odobreno!", HttpStatus.OK);
	}

}
