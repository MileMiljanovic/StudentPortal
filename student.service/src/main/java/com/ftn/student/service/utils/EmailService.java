package com.ftn.student.service.utils;

import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Zamena;
import com.itextpdf.text.DocumentException;

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

    private final Logger log = LoggerFactory.getLogger(EmailService.class);

    public void sendEmailStudent(Formular f) throws MessagingException, IOException, DocumentException {

        	
    	log.info("Email sending initiated!");

    	MimeMessage message = javaMailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message, true);

    	//helper.setTo(f.getStudent().getEmail());
    	helper.setTo("mile.miljanovic92@gmail.com");
    	helper.setSubject("Izveštaj o zahtevu za studiranje u inostranstvu");
    	helper.setText("Šef vašeg studijskog programa je odobrio vaš formular. "
    			+ "U prilogu se nalazi izveštaj o zahtevu za studiranje u inostranstvu.");

    	ByteArrayInputStream pdf = pdfGen.formularReport(f);

    	log.info("Attaching pdf...");
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
    
    public void sendEmailTeacher(Zamena z, Formular f) throws MessagingException {

    	
    	log.info("Email sending initiated!");
    	MimeMessage message = javaMailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message, true);

    	//helper.setTo(z.getPredmetDomaci().getNastavnik().getEmail());
    	helper.setTo("mile.miljanovic92@gmail.com");
    	helper.setSubject("Potvrda o zameni predmeta");

    	String token = z.getToken();

    	helper.setText("Student sa brojem indeksa " + f.getStudent().getBrindeksa() + " želi da zameni predmet "
    			+ z.getPredmetDomaci().getNaziv() + " na studijskom programu " + f.getStudent().getStudije().getNaziv()
    			+ " sa predmetom " + z.getPredmetStrani().getNaziv() + " na stranom studijskom programu " 
    			+ f.getProgramStrani().getNaziv() + ". Kliknite na link ispod da odobrite zamenu:\n\n" +
    			"http://localhost:8080/api/formulari/" + f.getIdformular() + "/zamene/" + z.getIdzamena() + "/" + token + "/Y\n\n" + "Kliknite na link ispod da odbijete zamenu:\n\n" 
    			+ "http://localhost:8080/api/formulari/" + f.getIdformular() + "/zamene/" + z.getIdzamena() + "/" + token + "/N\n\n");

    	javaMailSender.send(message);
    	log.info("Email successfully sent!");

    }
    
    public void sendEmailStudentConfirm(Formular f) throws MessagingException, IOException, DocumentException {

    	
    	log.info("Email sending initiated!");

    	MimeMessage message = javaMailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message, true);

    	//helper.setTo(f.getStudent().getEmail());
    	helper.setTo("mile.miljanovic92@gmail.com");
    	helper.setSubject("Obaveštenje o statusu formulara");
    	helper.setText("Poštovani, obaveštavamo vas da je vaš formular sa identifikacionom oznakom " + f.getIdformular()
    	+ " odobren od koordinatora departmana. Poslati su mejlovi svim predmetnim profesorima, molimo vas da sačekate njihova odobrenja");

    	javaMailSender.send(message);
    	log.info("Email successfully sent!");

    }
    
    public void sendEmailStudentReject(Formular f, String role) throws MessagingException, IOException, DocumentException {

    	
    	log.info("Email sending initiated!");

    	MimeMessage message = javaMailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
    	String roleString = "";
    	if (role.equals("SEF")) {
    		roleString += "šefa studijskog programa";
    	}
    	else {
    		roleString += "koordinatora departmana";
    	}

    	//helper.setTo(f.getStudent().getEmail());
    	helper.setTo("mile.miljanovic92@gmail.com");
    	helper.setSubject("Obaveštenje o statusu formulara");
    	helper.setText("Poštovani, obaveštavamo vas da je vaš formular sa identifikacionom oznakom " + f.getIdformular()
    	+ " odbijen od strane " + roleString + ". Molimo vas proverite sa službom razlog i izmenite formular.");

    	javaMailSender.send(message);
    	log.info("Email successfully sent!");

    }
    
    public void sendEmailStudentTeacher(Formular f, Zamena z, String odgovor) throws MessagingException, IOException, DocumentException {

    	
    	log.info("Email sending initiated!");

    	MimeMessage message = javaMailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
    	String status = "";
    	if (odgovor.equals("Y")) {
    		status += " odobrena.";
    	}
    	else {
    		status += " odbijena.";
    	}
    	
    	//helper.setTo(f.getStudent().getEmail());
    	helper.setTo("mile.miljanovic92@gmail.com");
    	helper.setSubject("Obaveštenje o statusu zamene");
    	helper.setText("Poštovani, obaveštavamo vas da je vaša zamena sa identifikacionom oznakom " + z.getIdzamena() + " u formularu "
    	+ z.getFormular() + status + " Konsultujte se sa vašim predmetnim profesorom ukoliko imate nekih pitanja.");

    	javaMailSender.send(message);
    	log.info("Email successfully sent!");

    }

}
