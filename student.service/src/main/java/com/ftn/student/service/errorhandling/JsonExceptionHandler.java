package com.ftn.student.service.errorhandling;

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

@ControllerAdvice
public class JsonExceptionHandler {
	
	@ExceptionHandler(RecoverableDataAccessException.class)
    @ResponseBody
    public ResponseEntity<Object> handleRecoverableDataAccessException(RecoverableDataAccessException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(exception.getMessage()));
    }
	
	@ExceptionHandler(NonTransientDataAccessException.class)
    @ResponseBody
    public ResponseEntity<Object> handleNonTransientDataAccessException(NonTransientDataAccessException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(exception.getMessage()));
    }
	
	@ExceptionHandler(TransientDataAccessException.class)
    @ResponseBody
    public ResponseEntity<Object> handleTransientDataAccessException(TransientDataAccessException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(exception.getMessage()));
    }
	
	@ExceptionHandler(ScriptException.class)
    @ResponseBody
    public ResponseEntity<Object> handleScriptException(ScriptException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(exception.getMessage()));
    }
	
	@ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleAllErrors(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(exception.getMessage()));
    }
}

