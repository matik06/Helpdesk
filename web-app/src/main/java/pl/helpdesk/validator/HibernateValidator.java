/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.validator;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author matik06
 */
//@Service
//@Scope("request")
public class HibernateValidator<T extends Object> {
    
    private Validator validator;
    
    public HibernateValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    public void validate(T entity) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            System.out.println("wrong validation: ");
            System.out.println(constraintViolation.getMessage());
            System.out.println(constraintViolation.getPropertyPath());
        }
    }
}
