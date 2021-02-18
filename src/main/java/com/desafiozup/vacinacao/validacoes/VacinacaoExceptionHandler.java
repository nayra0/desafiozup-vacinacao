package com.desafiozup.vacinacao.validacoes;

import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VacinacaoExceptionHandler {

	private MessageSource messageSource;

	public VacinacaoExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErrosResponse handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
		String mensagem = Optional.ofNullable(exception.getCause()).orElse(exception).toString();
		return criarErroResponse(mensagem);
	}

	private ErrosResponse criarErroResponse(String mensagem) {
		ErrosResponse errosReponse = new ErrosResponse();
		errosReponse.addErro(mensagem);
		return errosReponse;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrosResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		return criarErroResponse(exception.getBindingResult());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ErrosResponse handleBindException(BindException exception) {
		return criarErroResponse(exception.getBindingResult());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ValidationException.class)
	public ErrosResponse handleValidationException(ValidationException exception) {
		exception.printStackTrace();
		ErrosResponse errosResponse = new ErrosResponse();
		String mensagemErroValidacao = messageSource.getMessage("validation.error", null,
				LocaleContextHolder.getLocale());
		errosResponse.addErro(mensagemErroValidacao + ". " + exception.getLocalizedMessage());

		return errosResponse;
	}

	private ErrosResponse criarErroResponse(BindingResult bindingResult) {
		ErrosResponse errosReponse = new ErrosResponse();
		bindingResult.getGlobalErrors().forEach(error -> errosReponse.addErro(getErrorMessage(error)));

		bindingResult.getFieldErrors().forEach(erro -> {
			String mensagem = getErrorMessage(erro);
			errosReponse.addErroCampo(erro.getField(), mensagem);

		});

		return errosReponse;
	}

	private String getErrorMessage(ObjectError erro) {
		return messageSource.getMessage(erro, LocaleContextHolder.getLocale());
	}

}
