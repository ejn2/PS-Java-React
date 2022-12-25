package br.com.banco.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class DefaultException {
	
	private LocalDateTime timestamps = LocalDateTime.now();
	private Integer statusCode;
	private String message;
	
	public DefaultException(String message, HttpStatus status) {
		this.message = message;
		this.statusCode = status.value();
	}

}
