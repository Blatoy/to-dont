package ch.hearc.todont.validators;

import ch.hearc.todont.datatransfer.UserDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// Based on https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
public class PasswordValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserDto user = (UserDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}