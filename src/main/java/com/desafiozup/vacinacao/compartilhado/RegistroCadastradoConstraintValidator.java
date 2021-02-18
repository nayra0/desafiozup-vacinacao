package com.desafiozup.vacinacao.compartilhado;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class RegistroCadastradoConstraintValidator implements ConstraintValidator<RegistroCadastrado, Object> {

	private EntityManager manager;
	private RegistroCadastrado registroCadastrado;

	public RegistroCadastradoConstraintValidator(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void initialize(RegistroCadastrado constraintAnnotation) {
		this.registroCadastrado = constraintAnnotation;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Assert.notNull(this.manager, "O entityManager não deve ser nulo");

		if (value == null) {
			return true;
		}

		String hql = String.format("select s from %s s where s.%s = :propriedade",
				this.registroCadastrado.classe().getSimpleName(), this.registroCadastrado.propriedade());

		Query query = manager.createQuery(hql);
		query.setParameter("propriedade", value);
		List<?> result = query.getResultList();

		Assert.isTrue(result.size() <= 1, "Você tem mais de um " + this.registroCadastrado.classe() + " com o atributo "
				+ this.registroCadastrado.propriedade() + " igual a " + value);

		return result != null && !result.isEmpty();
	}

}
