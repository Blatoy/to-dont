package ch.hearc.todont.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

// Based on https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {   
    /**
     * Default error message.
     * @return
     */
    String message() default "Invalid email";

    /**
     * Default groups.
     * @return
     */
    Class<?>[] groups() default {}; 

    /**
     * Default payload.
     */
    Class<? extends Payload>[] payload() default {};
}