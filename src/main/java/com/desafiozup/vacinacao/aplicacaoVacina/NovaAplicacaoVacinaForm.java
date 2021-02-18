package com.desafiozup.vacinacao.aplicacaoVacina;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.desafiozup.vacinacao.compartilhado.RegistroCadastrado;
import com.desafiozup.vacinacao.usuario.Usuario;

public class NovaAplicacaoVacinaForm {
	
	@NotBlank
	private String nomeVacina;
	
	@NotNull
	@RegistroCadastrado(classe = Usuario.class, propriedade = "id")
	private Long idUsuario;
	
	@NotNull
	@Past
	private LocalDate dataAplicacao;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}

	public String getNomeVacina() {
		return nomeVacina;
	}

	public AplicacaoVacina toModel(EntityManager manager) {
		Usuario usuario = manager.find(Usuario.class, this.idUsuario);
		AplicacaoVacina aplicacaoVacina = new AplicacaoVacina(this.nomeVacina, usuario, this.dataAplicacao);
		return aplicacaoVacina;
	}
	
}
