package com.desafiozup.vacinacao.aplicacaoVacina;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.desafiozup.vacinacao.usuario.Usuario;

@Entity
public class AplicacaoVacina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nomeVacina;

	@ManyToOne
	@NotNull
	private Usuario usuario;

	@NotNull
	@Past
	private LocalDate dataAplicacao;

	public AplicacaoVacina(@NotBlank String nomeVacina, @NotNull Usuario usuario,
			@NotNull @Past LocalDate dataAplicacao) {
		this.nomeVacina = nomeVacina;
		this.usuario = usuario;
		this.dataAplicacao = dataAplicacao;
	}

	public Long getId() {
		return id;
	}
	
}
