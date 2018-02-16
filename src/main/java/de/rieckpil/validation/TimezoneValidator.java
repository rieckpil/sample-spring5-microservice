package de.rieckpil.validation;

import java.util.Arrays;
import java.util.TimeZone;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimezoneValidator implements ConstraintValidator<ValidTimezone, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return Arrays.asList(TimeZone.getAvailableIDs()).contains(value);
  }

}

