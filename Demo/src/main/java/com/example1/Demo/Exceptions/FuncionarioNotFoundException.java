package com.example1.Demo.Exceptions;

public class FuncionarioNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FuncionarioNotFoundException(Long id){
		super("Não foi possivel encontrar o funcionario " + id);
	}
}
