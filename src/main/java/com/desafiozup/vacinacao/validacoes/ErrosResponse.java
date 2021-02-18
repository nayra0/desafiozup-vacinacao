package com.desafiozup.vacinacao.validacoes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

public class ErrosResponse {

	private List<String> erros;
	private List<ErroCampoResponse> errosCampos;
	
	public ErrosResponse() {
		this.erros = new ArrayList<>();
		this.errosCampos = new ArrayList<>();
	}

	public boolean addErro(String error) {
		Assert.notNull(this.erros, "Erros não deve ser nulo");
		return this.erros.add(error);
	}
	
	public boolean addErroCampo(String campo, String mensagem) {
		Assert.notNull(this.erros, "errosCampos não deve ser nulo");
		return this.errosCampos.add(new ErroCampoResponse(campo, mensagem));
	}

	public List<String> getErros() {
		return erros;
	}

	public List<ErroCampoResponse> getErrosCampos() {
		return errosCampos;
	}
	
}
