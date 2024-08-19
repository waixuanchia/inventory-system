package com.example.inverntory_management_system.validation;

import com.example.inverntory_management_system.annotation.ForeignKeyRequired;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ForeignKeyValidator implements ConstraintValidator<ForeignKeyRequired,Object> {

  @Autowired
  private EntityManager entityManager;

  private Class<?> entityType;

  @Override
  public void initialize(ForeignKeyRequired constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.entityType = constraintAnnotation.entityType();
  }

  @Override
  public boolean isValid(Object t, ConstraintValidatorContext constraintValidatorContext) {
    if(t == null){
      return false;
    }
    else{
      try {
        Method method = this.entityManager.getClass().getMethod("find",Class.class,Object.class);
        Method getIdMethod = t.getClass().getMethod("getId");
        Object Id = getIdMethod.invoke(t);
        constraintValidatorContext.disableDefaultConstraintViolation();
        if(Id == null){

          constraintValidatorContext.buildConstraintViolationWithTemplate(t.getClass().getSimpleName() +" is required").addConstraintViolation();
          return false;
        }
        else{
          Object object = method.invoke(this.entityManager,entityType,Id);
          if(object == null){
            constraintValidatorContext.buildConstraintViolationWithTemplate("invalid" + t.getClass().getSimpleName());
            return false;
          }
        }

        return true;

      } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
        return false;
        //throw new RuntimeException(e);
      }

    }
  }
}
