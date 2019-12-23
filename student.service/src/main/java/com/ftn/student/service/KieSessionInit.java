package com.ftn.student.service;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.ftn.student.service.models.Formular;
import com.ftn.student.service.repository.FormularRepository;

@Component
public class KieSessionInit {
	
	@Autowired
	private KieContainer kieContainer;
	
	@Autowired
	private FormularRepository repoFormular;
	
	@Bean
	public KieSession fetchSession() {
		
		KieSession kieSession = kieContainer.newKieSession();
		return kieSession;
	}
	
	@Bean
	public void test() {
		
		List<Formular> f = repoFormular.findUnconfirmed();
		for (Formular fr: f) {
			System.out.println(fr.getIdformular());
		}
		
	}

}
