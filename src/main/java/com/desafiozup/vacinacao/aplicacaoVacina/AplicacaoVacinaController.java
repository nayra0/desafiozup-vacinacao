package com.desafiozup.vacinacao.aplicacaoVacina;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/vacinacao")
public class AplicacaoVacinaController {
	
	private EntityManager manager;

	public AplicacaoVacinaController(EntityManager manager) {
		this.manager = manager;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@RequestBody @Valid NovaAplicacaoVacinaForm form){
		AplicacaoVacina novaAplicacaoVacina = form.toModel(this.manager);
		this.manager.persist(novaAplicacaoVacina);
		
		URI uriNovaAplicacaoVacina = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(novaAplicacaoVacina.getId()).toUri();
		
		return ResponseEntity.created(uriNovaAplicacaoVacina).build();
	}

}
