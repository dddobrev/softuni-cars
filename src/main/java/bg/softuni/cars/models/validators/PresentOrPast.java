package bg.softuni.cars.models.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD, METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy = PresentOrPastValidator.class)
public @interface PresentOrPast {
  String message() default "This year is invalid";
  int minYear();
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
