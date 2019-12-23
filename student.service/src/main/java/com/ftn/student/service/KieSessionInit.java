package com.ftn.student.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KieSessionInit {
	
	@Autowired
	private KieContainer kieContainer;
	
	@Bean
	public KieSession fetchSession() {
		
		KieSession kieSession = kieContainer.newKieSession();
		return kieSession;
	}

}
