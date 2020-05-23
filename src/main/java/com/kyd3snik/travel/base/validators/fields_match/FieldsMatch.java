package com.kyd3snik.travel.base.validators.fields_match;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldsMatchValidator.class)
@Documented
public @interface FieldsMatch {
    String message() default "Fields must match";

    String[] fields();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
