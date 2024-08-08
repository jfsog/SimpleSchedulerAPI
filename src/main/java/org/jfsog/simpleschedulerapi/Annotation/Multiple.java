package org.jfsog.simpleschedulerapi.Annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.jfsog.simpleschedulerapi.Validadores.MultiploValidador;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultiploValidador.class)
public @interface Multiple {
    String message() default "O valor deve ser múltiplo do número especificado e estar dentro do intervalo permitido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int valor(); // Parâmetro para o múltiplo
    int min() default 1; // Valor mínimo
    int max() default Integer.MAX_VALUE; // Valor máximo
}
