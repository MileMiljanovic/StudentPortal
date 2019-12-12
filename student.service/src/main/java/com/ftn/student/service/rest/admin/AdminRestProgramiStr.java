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
import com.ftn.student.service.models.StudijskiProgramStrani;
import com.ftn.student.service.repository.SProgramStraniRepository;

@RestController
public class AdminRestProgramiStr {
	
	@Autowired
	private SProgramStraniRepository repoStrani;
	
	@RequestMapping(value = "/progStrani", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<StudijskiProgramStrani>> progStrani() {

		List<StudijskiProgramStrani> progStr = repoStrani.findAll();
		
		return new ResponseEntity<List<StudijskiProgramStrani>>(progStr, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addProgStrani", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> addProgStrani(@Valid @RequestBody StudijskiProgramStrani request) {

		Optional<StudijskiProgramStrani> progStr = repoStrani.findById(request.getNaziv());
		if (progStr.isPresent()) {
			return new ResponseEntity<String>("Program (strani) vec postoji!", HttpStatus.BAD_REQUEST);
		}
		repoStrani.save(request);
		return new ResponseEntity<String>("Program (strani) uspesno dodat!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateProgStrani", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> updateProgStrani(@Valid @RequestBody StudijskiProgramStrani request) {

		repoStrani.save(request);
		return new ResponseEntity<String>("Program (strani) uspesno izmenjen!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteProgStrani", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteProgStrani(@Valid @RequestBody StudijskiProgramStrani request) {

		Optional<StudijskiProgramStrani> progStr = repoStrani.findById(request.getNaziv());
		if (!progStr.isPresent()) {
			return new ResponseEntity<String>("Program (strani) ne postoji!", HttpStatus.BAD_REQUEST);
		}
		repoStrani.delete(request);
		return new ResponseEntity<String>("Program (strani) uspesno obrisan!", HttpStatus.OK);
	}

}