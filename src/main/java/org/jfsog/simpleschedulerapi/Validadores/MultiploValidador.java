package org.jfsog.simpleschedulerapi.Validadores;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jfsog.simpleschedulerapi.Annotation.Multiple;

public class MultiploValidador implements ConstraintValidator<Multiple, Long> {
    private int valor;
    private int min;
    private int max;
    @Override
    public void initialize(Multiple constraintAnnotation) {
        this.valor = constraintAnnotation.valor();
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && value > 0 && value % valor == 0 && value >= min && value <= max;
    }
}
