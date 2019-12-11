package com.ftn.student.service.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Zamena;
import com.ftn.student.service.repository.FormularRepository;
import com.ftn.student.service.repository.ZamenaRepository;

@Component
@EnableScheduling
@EnableAsync
public class CronUpdateZamena {
	
	@Autowired
	private FormularRepository repoFormular;
	
	@Autowired
	private ZamenaRepository repoZamena;
	
	private final Logger log = LoggerFactory.getLogger(CronUpdateZamena.class);
	
	@Async
	@Scheduled(cron = "0 0/10 * * * *")
    public void updateZamena() {
		
		List<Formular> formulari = repoFormular.findAll();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
	    cal.setTimeInMillis(now.getTime());
	    cal.setTimeInMillis(now.getTime());
	    cal.add(Calendar.DAY_OF_MONTH, -1);
	    Timestamp dayAgo = new Timestamp(cal.getTime().getTime());
	    
		for (Formular f: formulari) {
			if (f.getDatum().before(dayAgo)) {
				if (f.getOdobrenjeSef() == null && f.getOdobrenjeKoord().equals("Y")) {
					for (Zamena z: f.getZamene()) {
						if (z.getOdobreno() == null) {
							z.setOdobreno("Y");
							repoZamena.save(z);
							log.info("Zamena " + z.getIdzamena() + " odobrena!");
						}
					}
				}
			}
		}
		
    }

}
