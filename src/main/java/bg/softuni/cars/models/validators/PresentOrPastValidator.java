package bg.softuni.cars.models.validators;

import java.time.YearMonth;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PresentOrPastValidator implements ConstraintValidator<PresentOrPast, Integer> {

  private int minYear;

  @Override
  public void initialize(PresentOrPast constraintAnnotation) {
    this.minYear = constraintAnnotation.minYear();
  }

  @Override
  public boolean isValid(Integer value, ConstraintValidatorContext context) {
    return value != null &&
        value >= minYear &&
        value <= YearMonth.now().getYear();
  }


}
