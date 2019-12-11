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
import com.ftn.student.service.models.StudijskiProgramDomaci;
import com.ftn.student.service.repository.SProgramDomaciRepository;

@RestController
public class AdminRestProgramiDom {
	
	@Autowired
	private SProgramDomaciRepository repoDomaci;
	
	@RequestMapping(value = "/progDomaci", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<StudijskiProgramDomaci>> progDomaci() {

		List<StudijskiProgramDomaci> progDom = repoDomaci.findAll();
		
		return new ResponseEntity<List<StudijskiProgramDomaci>>(progDom, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addProgDomaci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addProgDomaci(@Valid @RequestBody StudijskiProgramDomaci request) {

		Optional<StudijskiProgramDomaci> progDom = repoDomaci.findById(request.getNaziv());
		if (progDom.isPresent()) {
			return new ResponseEntity<String>("Program (domaci) vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDomaci.save(request);
		return new ResponseEntity<String>("Program (domaci) uspesno dodat!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateProgDomaci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updateProgDomaci(@Valid @RequestBody StudijskiProgramDomaci request) {

		repoDomaci.save(request);
		return new ResponseEntity<String>("Program (domaci) uspesno izmenjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteProgDomaci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteProgDomaci(@Valid @RequestBody StudijskiProgramDomaci request) {

		Optional<StudijskiProgramDomaci> progDom = repoDomaci.findById(request.getNaziv());
		if (!progDom.isPresent()) {
			return new ResponseEntity<String>("Program (domaci) ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoDomaci.delete(request);
		return new ResponseEntity<String>("Program (domaci) uspesno obrisan!", HttpStatus.OK);
	}

}
