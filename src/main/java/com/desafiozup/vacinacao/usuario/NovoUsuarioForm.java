package com.desafiozup.vacinacao.usuario;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

import com.desafiozup.vacinacao.compartilhado.Unique;
import com.sun.istack.NotNull;

public class NovoUsuarioForm {

	@NotBlank
	private String nome;
	
	@NotBlank
	@Email
	@Unique(entidade = Usuario.class, propriedade = "email")
	private String email;
	
	@NotNull
	@CPF
	@Unique(entidade = Usuario.class, propriedade = "cpf")
	private String cpf;
	
	@NotNull
	@Past
	private LocalDate dataNascimento;

	public Usuario toModel() {
		return new Usuario(this.nome, this.email, this.cpf, this.dataNascimento);
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
}
