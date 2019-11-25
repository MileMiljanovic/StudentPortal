package com.ftn.student.service.emailservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftn.student.service.models.Formular;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private final Logger log = LoggerFactory.getLogger(EmailService.class);

    private ArrayList<File> toDelete;

    @PostConstruct
    void init() throws SecurityException, IOException {
        toDelete = new ArrayList<File>();
        log.info("Email service initialized!");
    }

    public void sendEmail() {
        try {

            /*String json = httpEntity.getBody();
            log.info("Rest request arrived! Body: " + json);
           
            ObjectMapper objectMapper = new ObjectMapper();

            Formular body = objectMapper.readValue(json, Formular.class);*/

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo("mile.miljanovic@devoteam.com");
            helper.setSubject("Test subject");
            helper.setText("This is a test message");
            /*if (body.getAttachments() != null) {
                for (Attachment a: body.getAttachments()) {
                    File file = new File(a.getContentName());
                    byte[] bytes = a.getContent().getBytes();
                    OutputStream os = new FileOutputStream(file);
                    os.write(bytes);
                    helper.addAttachment(a.getContentName() + "." + a.getContentType(), file);
                    os.close();
                    toDelete.add(file);
                }
            }*/

            javaMailSender.send(message);
            /*log.info("Email successfully sent! From: " + body.getFrom() + " To: " + body.getTo()
                    + "; With subject: " + body.getSubject());*/

            for (File f: toDelete) {
                f.delete();
            }

            toDelete.clear();

            //return new ResponseEntity<String>("OK", HttpStatus.OK);
        }

        catch (MessagingException e) {
            log.error("MessagingException has occured! CAUSE: " + e.getCause() + "; MESSAGE: " + e.getMessage());
            //return new ResponseEntity<String>("Failed due to MessagingException: " + e.getCause() + "; " + e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }

        catch (Exception e) {
            log.error("Generic exception has occured! CAUSE: " + e.getCause() + "; MESSAGE: " + e.getMessage());
            //return new ResponseEntity<String>("Failed due to exception: " + e.getCause() + "; " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
