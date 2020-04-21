package ch.hearc.todont.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

// Based on https://www.baeldung.com/registration-with-spring-mvc-and-spring-security

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE}) 
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface PasswordMatches { 
    /**
     * Default error message.
     * @return
     */
    String message() default "Passwords don't match";
    
    /**
     * Default group.
     * @return
     */
    Class<?>[] groups() default {}; 
    
    /**
     * Default payload.
     * @return
     */
    Class<? extends Payload>[] payload() default {};
}