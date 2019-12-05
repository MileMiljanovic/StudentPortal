package com.ftn.student.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.ftn.student.service.emailservice.EmailService;
import com.ftn.student.service.models.Departman;
import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Korisnik;
import com.ftn.student.service.models.PredmetDomaci;
import com.ftn.student.service.models.PredmetID;
import com.ftn.student.service.models.PredmetStrani;
import com.ftn.student.service.models.Student;
import com.ftn.student.service.models.StudijskiProgramDomaci;
import com.ftn.student.service.models.StudijskiProgramStrani;
import com.ftn.student.service.models.Zamena;
import com.ftn.student.service.repository.DepartmaniRepository;
import com.ftn.student.service.repository.FormularRepository;
import com.ftn.student.service.repository.KorisniciRepository;
import com.ftn.student.service.repository.PredmetiDomaciRepository;
import com.ftn.student.service.repository.PredmetiStraniRepository;
import com.ftn.student.service.repository.SProgramDomaciRepository;
import com.ftn.student.service.repository.SProgramStraniRepository;
import com.ftn.student.service.repository.StudentRepository;
import com.ftn.student.service.repository.ZamenaRepository;

@Service
public class TestService {

	@Autowired
	private KieContainer kieContainer;
	
	@Autowired
	private KorisniciRepository repoKorisnici;
	
	@Autowired
	private DepartmaniRepository repoDepartmani;
	
	@Autowired
	private SProgramDomaciRepository repoDomaci;
	
	@Autowired
	private SProgramStraniRepository repoStrani;
	
	@Autowired
	private StudentRepository repoStudent;
	
	@Autowired
	private PredmetiDomaciRepository repoPD;
	
	@Autowired
	private PredmetiStraniRepository repoPS;
	
	@Autowired
	private FormularRepository repoF;
	
	@Autowired
	private ZamenaRepository repoZ;
	
	@Autowired
	private EmailService emailService;
	
	@Bean
	public void testing() {
		
		KieSession kieSession = kieContainer.newKieSession();
		
		/*for (Korisnik k: repoKorisnici.findAll()) {
			kieSession.insert(k);
			System.out.println(k.getUsername() + " " + k.getIme() + " " + k.getPrezime() + " " + k.getUloga() + " " + k.getDatumrodjenja());
		}
		
		for (Departman d: repoDepartmani.findAll()) {
			System.out.println(d.getDepartmanId() + " " + d.getKoordinator().getUsername());
		}
		
		for (StudijskiProgramDomaci sd: repoDomaci.findAll()) {
			System.out.println(sd.getNaziv() + " " + sd.getDepartman().getDepartmanId() + " " + sd.getSef().getUsername());
		}
		
		for (StudijskiProgramStrani ss: repoStrani.findAll()) {
			System.out.println(ss.getNaziv());
		}
		
		for (Student st: repoStudent.findAll()) {
			System.out.println(st.getBrindeksa() + " " + st.getStudije().getNaziv());
		}
		*/
		StudijskiProgramDomaci geo = repoDomaci.findById("Geodezija i geomatika").get();
		
		for (PredmetDomaci pd: repoPD.findAll()) {
			System.out.println(pd.getNaziv() + " " + pd.getProgram().getNaziv());
		}
		
		for (PredmetStrani ps: repoPS.findAll()) {
			System.out.println(ps.getNaziv() + " " + ps.getProgram().getNaziv());
		}
		
		for (Formular f: repoF.findAll()) {
			System.out.println(f.getIdformular());
		}
		
		for (Zamena z: repoZ.findAll()) {
			System.out.println(z.getIdzamena());
		}
		
		//emailService.sendEmail();
		
		/*Formular f = repoF.findById("F2211").get();

		f.setOdobrenjeKoord('Y');
		f.setOdobrenjeSef('Y');
		repoF.save(f);
		
		/*Student s = repoStudent.findById("EE78/2014").get();
		Timestamp t = new Timestamp(0);
		Formular f1 = new Formular("F57712", s, 'N', 'N', t, null);
		repoF.save(f1);
		
		kieSession.fireAllRules(); 
		kieSession.dispose();*/
		
		/*List<PredmetDomaci> pred = repoPD.findByProgram(geo);
		
		for (PredmetDomaci p: pred) {
			System.out.println(p.getNaziv());
		}*/
		
	}
	
}
