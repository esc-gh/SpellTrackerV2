package com.bae.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = { SpellNotFoundException.class })
	public ResponseEntity<String> spellNotFoundExceptions(SpellNotFoundException snfe) {
		return new ResponseEntity<String>(snfe.getMessage(), HttpStatus.NOT_FOUND);
	}

}
