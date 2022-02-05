package com.example.pictgram.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

@Documented
@Constraint(validatedBy = PasswordEqualsValidator.class)
@Target({ ElementType.TYPE }) //어노테이션을 부여할 수 있는 대상
@Retention(RetentionPolicy.RUNTIME) //어노테이션 정보가 유지되는 범위  'Retention = 유지'
@ReportAsSingleViolation
public @interface PasswordEquals { //어노테이션 정의

    String message() default "{com.example.pictgram.validation.constraints.PasswordEquals.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        PasswordEquals[] value();
    }
}