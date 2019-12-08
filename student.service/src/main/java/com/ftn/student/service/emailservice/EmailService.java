package com.ftn.student.service.emailservice;

import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Zamena;
import com.ftn.student.service.models.ZamenaToken;
import com.ftn.student.service.pdfservice.PDFGenerator;
import com.ftn.student.service.repository.FormularRepository;
import com.ftn.student.service.repository.ZamenaTokenRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private PDFGenerator pdfGen;
    
    @Autowired
	private ZamenaTokenRepository repoZT;
    
    @Autowired
	private FormularRepository repoFormular;

    private final Logger log = LoggerFactory.getLogger(EmailService.class);

    public void sendEmailStudent(Formular f) {
    	
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            //helper.setTo(f.getStudent().getEmail());
            helper.setTo("mile.miljanovic92@gmail.com");
            helper.setSubject("Izveštaj o zahtevu za studiranje u inostranstvu");
            helper.setText("U prilogu se nalazi izveštaj o zahtevu za studiranje u inostranstvu. Klikni u attachment retarde");
            
            ByteArrayInputStream pdf = pdfGen.formularReport(f);
            
            File file = new File("FormularReport");
            FileOutputStream fos = new FileOutputStream(file);
            int data;
            while (( data = pdf.read()) != -1) {
            	
	            char ch = (char)data;
	            fos.write(ch);
            }
            
            fos.flush();
            fos.close();
            helper.addAttachment("FormularReport.pdf", file);


            javaMailSender.send(message);
            log.info("Email successfully sent!");

            file.delete();

        }

        catch (MessagingException e) {
            log.error("MessagingException has occured! CAUSE: " + e.getCause() + "; MESSAGE: " + e.getMessage());
            
        } 
        
        catch (IOException e) {
        	log.error("IOException has occured! CAUSE: " + e.getCause() + "; MESSAGE: " + e.getMessage());
		}

    }
    
    public void sendEmailTeacher(Zamena z) {
    	
    	try {
    	
    		MimeMessage message = javaMailSender.createMimeMessage();
    		MimeMessageHelper helper = new MimeMessageHelper(message, true);

    		//helper.setTo(z.getPredmetDomaci().getNastavnik().getEmail());
    		helper.setTo("mile.miljanovic92@gmail.com");
    		helper.setSubject("Potvrda o zameni predmeta");
    		
    		ZamenaToken zt = repoZT.findById(z.getIdzamena()).get();
    		String token = zt.getToken();
    		
    		Formular f = repoFormular.findById(z.getFormular()).get();
    		
    		//To do: link za potvrdu ili odbijanje
    		helper.setText("Student sa brojem indeksa " + f.getStudent().getBrindeksa() + " želi da zameni predmet "
    				+ z.getPredmetDomaci() + " na studijskom programu " + z.getStudijskiProgramDomaci()
    				+ " sa predmetom " + z.getPredmetStrani() + " na stranom studijskom programu " 
    				+ z.getStudijskiProgramStrani() + ". Kliknite na link ispod da odobrite zamenu:\n\n" +
    				"http://localhost:8080/teacherConfirm/" + token + "/" + z.getIdzamena() + "/Y\n\n" + "Kliknite na link ispod da odbijete zamenu:\n\n" 
    				+ "http://localhost:8080/teacherConfirm/" + token + "/" + z.getIdzamena() + "/N\n\n");
         
         
    		javaMailSender.send(message);
    		log.info("Email successfully sent!");
         
    	}

        catch (MessagingException e) {
            log.error("MessagingException has occured! CAUSE: " + e.getCause() + "; MESSAGE: " + e.getMessage());
        }


    }

}
