package com.ftn.student.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.ftn.student.service.models.Country;
import com.ftn.student.service.models.TestModel;
import com.ftn.student.service.repository.TestRepository;

@Service
public class TestService {

	@Autowired
	private KieContainer kieContainer;
	
	@Autowired
	private TestRepository repo;
	
	@Bean
	public void testing() {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(new TestModel("pera", 35));
		kieSession.fireAllRules(); 
		kieSession.dispose();
		
		
		for(Country c: repo.findAll()) {
			System.out.println(c.getCountry() + " " + c.getLastUpdate());
		}
		
	}
	
}
