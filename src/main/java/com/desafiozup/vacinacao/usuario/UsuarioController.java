package com.desafiozup.vacinacao.usuario;

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
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private EntityManager manager;

	public UsuarioController(EntityManager manager) {
		this.manager = manager;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@RequestBody @Valid NovoUsuarioForm form){
		Usuario novoUsuario = form.toModel();
		this.manager.persist(novoUsuario);
		
		URI uriNovoUsuario = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(novoUsuario.getId()).toUri();
		
		return ResponseEntity.created(uriNovoUsuario).build();
	}

}
