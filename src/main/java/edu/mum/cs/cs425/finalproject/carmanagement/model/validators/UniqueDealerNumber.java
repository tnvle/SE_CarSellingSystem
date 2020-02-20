package edu.mum.cs.cs425.finalproject.carmanagement.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDealerNumberValidator.class)
public @interface UniqueDealerNumber {
    String message() default "{edu.mum.cs.cs425.customvalidators.uniquedealernumber.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
