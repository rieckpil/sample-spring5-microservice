package de.rieckpil.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  /**
   * POINTCUT EXPRESSION LANGUAGE:
   * 
   * execution(modifiers-pattern? return-type-pattern declaring-type-pattern? method-name-pattern(param-pattern) throws-pattern?)
   * 
   * ? = optional
   * 
   * param-pattern:
   * 
   * () - match a method with no arguments
   * (*) - match a method with one argument of any type
   * (..) - match a method with 0 or more arguments of any type
   * 
   */

  @Before("execution(public int getAmountOfCountries())")
  public void beforeGetAllCountries() {
    log.info("BEFORE: getAmountOfCountries() called at: " + new Date().getTime());
  }

  @After("execution(public int getAmountOfCountries())")
  public void afterGetAllCountries() {
    log.info("AFTER: getAmountOfCountries() called at: " + new Date().getTime());
  }
  
  @Before("execution(public * find*())")
  public void logAllFindMethodsWithoutParameter() {
    log.info("BEFORE: find*() called at: " + new Date().getTime());
  }
  
  @Before("execution(public * find*(*))")
  public void logAllFindMethodsWithParameter() {
    log.info("BEFORE: find*(*) called at: " + new Date().getTime());
  }

}
