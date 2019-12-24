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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.PredmetDomaci;
import com.ftn.student.service.models.PredmetStrani;
import com.ftn.student.service.models.Zamena;
import com.ftn.student.service.repository.FormularRepository;
import com.ftn.student.service.repository.PredmetiDomaciRepository;
import com.ftn.student.service.repository.PredmetiStraniRepository;
import com.ftn.student.service.repository.ZamenaRepository;
import com.ftn.student.service.rest.responsesadmin.AdminZamenaResponse;

@RestController
public class AdminRestZamene {
	
	@Autowired
	private ZamenaRepository repoZ;
	
	@Autowired
	private FormularRepository repoFormular;
	
	@Autowired
	private PredmetiDomaciRepository repoDomaci;
	
	@Autowired
	private PredmetiStraniRepository repoStrani;
	
	private final Logger log = LoggerFactory.getLogger(AdminRestZamene.class);
	
	@RequestMapping(value = "/zamene", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<AdminZamenaResponse> zamene() {

		List<Zamena> zam = repoZ.findAll();
		List<Formular> form = repoFormular.findAll();
		List<PredmetDomaci> dom = repoDomaci.findAll();
		List<PredmetStrani> str = repoStrani.findAll();
		
		AdminZamenaResponse response = new AdminZamenaResponse(zam, form, dom, str);
		
		return new ResponseEntity<AdminZamenaResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zamene/addZamena", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addZamena(@Valid @RequestBody Zamena request) {

		Optional<Zamena> zam = repoZ.findById(request.getIdzamena());
		if (zam.isPresent()) {
			log.error("Substitute already exists!");
			return new ResponseEntity<String>("Zamena vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoZ.save(request);
		log.info("Substitute successfully inserted!");
		return new ResponseEntity<String>("Zamena uspesno dodata!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zamene/updateZamena", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updateZamena(@Valid @RequestBody Zamena request) {

		Optional<Zamena> zam = repoZ.findById(request.getIdzamena());
		if (!zam.isPresent()) {
			log.error("Substitute does not exist!");
			return new ResponseEntity<String>("Zamena ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoZ.save(request);
		log.info("Substitute successfully updated!");
		return new ResponseEntity<String>("Zamena uspesno izmenjena!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zamene/deleteZamena", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteZamena(@Valid @RequestBody Zamena request) {

		Optional<Zamena> zam = repoZ.findById(request.getIdzamena());
		if (!zam.isPresent()) {
			log.error("Substitute does not exist!");
			return new ResponseEntity<String>("Zamena ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoZ.delete(request);
		log.info("Substitute successfully deleted!");
		return new ResponseEntity<String>("Zamena uspesno obrisana!", HttpStatus.OK);
	}

}
