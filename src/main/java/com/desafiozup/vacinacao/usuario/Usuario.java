package com.desafiozup.vacinacao.usuario;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank 
	private String nome;
	
	@NotNull
	@Email 
	private String email;
	
	@NotNull
	@CPF 
	private String cpf;
	
	@NotNull
	@Past 
	private LocalDate dataNascimento;

	@Deprecated
	public Usuario() {
	}

	public Usuario(@NotBlank String nome, @Email String email, @CPF String cpf, @Past LocalDate dataNascimento) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return id;
	}

}
