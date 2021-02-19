package com.example1.Demo.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example1.Demo.Exceptions.FuncionarioNotFoundException;
import com.example1.Demo.Models.Funcionario;
import com.example1.Demo.Repositories.FuncionarioRepository;

@RestController
public class FuncionarioController {
	
	private @Autowired FuncionarioRepository repository;
	
	@GetMapping("/funcionarios")
	List<Funcionario> all(){
		return repository.findAll();
	}
	
	@PostMapping("/funcionarios")
	public ResponseEntity<Funcionario> newFuncionario(@Valid @RequestBody Funcionario newFuncionario) {
		repository.save(newFuncionario);
		return new ResponseEntity<Funcionario>(newFuncionario, HttpStatus.CREATED);
	}
	
	@GetMapping("/funcionarios/{id}")
	Funcionario one(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new FuncionarioNotFoundException(id));
	}
	
	@PutMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> replaceFuncionario(@Valid @RequestBody Funcionario newFuncionario, @PathVariable Long id) {
		return repository.findById(id)
				.map(funcionario -> {
					funcionario.setNome(newFuncionario.getNome());
					funcionario.setCargo(newFuncionario.getCargo());
					repository.save(funcionario);
					return new ResponseEntity<Funcionario>(newFuncionario, HttpStatus.OK);
				})
				.orElseGet(() -> {
					newFuncionario.setId(id);
					repository.save(newFuncionario);
					return new ResponseEntity<Funcionario>(newFuncionario, HttpStatus.CREATED);
				});
	}
	
	@DeleteMapping("/funcionarios/{id}")
	void deleteFuncionario(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
