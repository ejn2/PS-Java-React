package br.com.banco.handler;

import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.banco.exceptions.BancoNotFoundException;
import br.com.banco.exceptions.DefaultException;

@RestControllerAdvice
public class HandlerExceptions extends ResponseEntityExceptionHandler {

	
	// ============================ [ Handler DateTime parse error ] ============================
	
	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<Object> handleDateTimeParseException() {
		return new ResponseEntity<>(new DefaultException("Formato de data invalido.", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}
	
	
	
	// ============================ [ Handler Custom Not Found ] ============================
	
	@ExceptionHandler(BancoNotFoundException.class)
	public ResponseEntity<Object> handleBancoNotFoundException(BancoNotFoundException ex) {
		return new ResponseEntity<>(new DefaultException(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.BAD_REQUEST);
		
	}
	
}
