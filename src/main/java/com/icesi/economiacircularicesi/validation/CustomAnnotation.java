package com.icesi.economiacircularicesi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

public interface CustomAnnotation {

    @Documented
    @Constraint(validatedBy = PasswordValidator.class)
    @Target({ ElementType.METHOD, ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    @interface PasswordValidation {



        String message() default "Invalid Password";


        Class<?>[] groups() default {};


        Class<? extends Payload>[] payload() default {};

    }


}