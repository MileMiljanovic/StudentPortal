package com.ftn.student.service.rest.users;

import java.util.ArrayList;
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

import com.ftn.student.service.emailservice.EmailService;
import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Korisnik;
import com.ftn.student.service.models.Uloga;
import com.ftn.student.service.models.Zamena;
import com.ftn.student.service.models.ZamenaToken;
import com.ftn.student.service.repository.FormularRepository;
import com.ftn.student.service.repository.KorisniciRepository;
import com.ftn.student.service.repository.ZamenaRepository;
import com.ftn.student.service.repository.ZamenaTokenRepository;
import com.ftn.student.service.rest.requests.ConfirmationRequest;
import com.ftn.student.service.rest.requests.UserLoginRequest;
import com.ftn.student.service.rest.responses.UserLoginResponse;

@RestController
public class UserRest {
	
	@Autowired
	private KorisniciRepository repoKorisnici;
	
	@Autowired
	private FormularRepository repoFormular;
	
	@Autowired
	private ZamenaRepository repoZamena;
	
	@Autowired
	private ZamenaTokenRepository repoZT;
	
	@Autowired
	private EmailService emailSvc;
	
	private final Logger log = LoggerFactory.getLogger(UserRest.class);
	
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<UserLoginResponse> userLogin(@Valid @RequestBody UserLoginRequest request) {

		Optional<Korisnik> kor = repoKorisnici.findById(request.getUsername());
		if (!kor.isPresent()) {
			return new ResponseEntity<UserLoginResponse>(HttpStatus.UNAUTHORIZED);
		}
		
		Korisnik k = kor.get();
		if (!k.getPassword().equals(request.getPassword())) {
			return new ResponseEntity<UserLoginResponse>(HttpStatus.UNAUTHORIZED);
		}
		
		UserLoginResponse response = new UserLoginResponse();
		List<Formular> formulari = repoFormular.findAll();
		
		if (k.getUloga().equals(Uloga.ADMIN)) {
			response.setKorisnik(k);
			response.setFormulari(null);
			log.info("Successfully logged in as: " + k.getUsername());
			return new ResponseEntity<UserLoginResponse>(response, HttpStatus.OK);
		}
		
		else if (k.getUloga().equals(Uloga.KOORDINATOR)) {
			List<Formular> koordFormulari = new ArrayList<Formular>();
			for (Formular f: formulari) {
				if (f.getOdobrenjeKoord() == null && 
						f.getStudent().getStudije().getDepartman().getKoordinator().equals(k)) {
					koordFormulari.add(f);
				}
			}
			response.setKorisnik(k);
			response.setFormulari(koordFormulari);
			log.info("Successfully logged in as: " + k.getUsername());
			return new ResponseEntity<UserLoginResponse>(response,  HttpStatus.OK);
		}
		
		else {
			List<Formular> sefFormulari = new ArrayList<Formular>();
			for (Formular f: formulari) {
				if (f.getOdobrenjeSef() == null && f.getOdobrenjeKoord() != null 
						&& f.getOdobrenjeKoord().equals("Y") && f.getStudent().getStudije().getSef().equals(k)) {
					List<Zamena> zamene = repoZamena.findByFormular(f);
					boolean sve = true;
					for(Zamena z: zamene) {
						if (z.getOdobreno() == null || z.getOdobreno().equals("N")) {
							sve = false;
							break;
						}
					}
					if (sve) {
						sefFormulari.add(f);
					}
				}
			}
			response.setKorisnik(k);
			response.setFormulari(sefFormulari);
			log.info("Successfully logged in as: " + k.getUsername());
			return new ResponseEntity<UserLoginResponse>(response,  HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/coordinatorConfirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> coordinatorConfirm(@Valid @RequestBody ConfirmationRequest request) {

		Formular f = request.getFormularId();
		f.setOdobrenjeKoord(request.getOdgovor());
		repoFormular.save(f);
		for (Zamena z: f.getZamene()) {
			emailSvc.sendEmailTeacher(z, f);
		}
		log.info("Successfully confirmed a formular! ID: " + f.getIdformular());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/headConfirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> headConfirm(@Valid @RequestBody ConfirmationRequest request) {
	
		Formular f = request.getFormularId();
		f.setOdobrenjeSef(request.getOdgovor());
		repoFormular.save(f);
		emailSvc.sendEmailStudent(f);
		log.info("Successfully confirmed a formular! ID: " + f.getIdformular());
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
		log.info("Successfully confirmed a change! ID: " + zam.getIdzamena());
		return new ResponseEntity<String>("Uspesno odobreno!", HttpStatus.OK);
	}

}