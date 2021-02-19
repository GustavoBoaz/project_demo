package com.example1.Demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FuncionarioNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(FuncionarioNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String funcionarioNotFoundHandler(FuncionarioNotFoundException ex) {
		return ex.getMessage();
	}
}
