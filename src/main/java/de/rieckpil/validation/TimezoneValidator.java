package de.rieckpil.validation;

import java.util.TimeZone;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.assertj.core.util.Arrays;

public class TimezoneValidator implements ConstraintValidator<ValidTimezone, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return Arrays.asList(TimeZone.getAvailableIDs()).contains(value);
  }

}

