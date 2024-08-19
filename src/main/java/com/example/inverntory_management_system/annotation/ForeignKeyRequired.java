package com.example.inverntory_management_system.annotation;


import com.example.inverntory_management_system.validation.ForeignKeyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = {ForeignKeyValidator.class})
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ForeignKeyRequired {
  String message() default "";

  Class<?> repository();

  Class<?> entityType();
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
