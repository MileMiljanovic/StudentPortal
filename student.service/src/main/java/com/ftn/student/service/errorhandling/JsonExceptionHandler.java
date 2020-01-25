package com.ftn.student.service.errorhandling;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.script.ScriptException;

import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.DocumentException;

@ControllerAdvice
public class JsonExceptionHandler {
	
	@ExceptionHandler(MessagingException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMessagingException(MessagingException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new JsonResponse("MessagingException has occured! Cause: "+ exception.getCause() + 
                		" Message: " + exception.getMessage()));
    }
	
	@ExceptionHandler(DocumentException.class)
    @ResponseBody
    public ResponseEntity<Object> handleDocumentException(DocumentException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new JsonResponse("DocumentException has occured! Cause: "+ exception.getCause() + 
                		" Message: " + exception.getMessage()));    }
	

	@ExceptionHandler(IOException.class)
    @ResponseBody
    public ResponseEntity<Object> handleIOException(IOException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new JsonResponse("IOException has occured! Cause: "+ exception.getCause() + 
                		" Message: " + exception.getMessage()));    }
	
	@ExceptionHandler(RecoverableDataAccessException.class)
    @ResponseBody
    public ResponseEntity<Object> handleRecoverableDataAccessException(RecoverableDataAccessException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new JsonResponse("RecoverableDataAccessException has occured! Cause: "+ exception.getCause() + 
                		" Message: " + exception.getMessage()));    }
	
	@ExceptionHandler(NonTransientDataAccessException.class)
    @ResponseBody
    public ResponseEntity<Object> handleNonTransientDataAccessException(NonTransientDataAccessException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new JsonResponse("NonTransientDataAccessException has occured! Cause: "+ exception.getCause() + 
                		" Message: " + exception.getMessage()));    }
	
	@ExceptionHandler(TransientDataAccessException.class)
    @ResponseBody
    public ResponseEntity<Object> handleTransientDataAccessException(TransientDataAccessException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new JsonResponse("TransientDataAccessException has occured! Cause: "+ exception.getCause() + 
                		" Message: " + exception.getMessage()));    }
	
	@ExceptionHandler(ScriptException.class)
    @ResponseBody
    public ResponseEntity<Object> handleScriptException(ScriptException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new JsonResponse("ScriptException has occured! Cause: "+ exception.getCause() + 
                		" Message: " + exception.getMessage()));    }
	
	@ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleAllErrors(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new JsonResponse("Generic Exception has occured! Cause: "+ exception.getCause() + 
                		" Message: " + exception.getMessage()));    }
}

