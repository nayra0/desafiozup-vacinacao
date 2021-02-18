package com.desafiozup.vacinacao.compartilhado;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = RegistroCadastradoConstraintValidator.class)
public @interface RegistroCadastrado {

    String message() default "{record.not.exist}";
	
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    Class<?> classe();
    
    String propriedade();
    
}
