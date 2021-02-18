package com.desafiozup.vacinacao.validacoes;

public class ErroCampoResponse {

	private String campo;
	private String mensagem;

	public ErroCampoResponse(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}
	
}
