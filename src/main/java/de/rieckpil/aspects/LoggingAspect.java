package de.rieckpil.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.Date;

@Aspect
@Component
@Slf4j
@Order(2)
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
   * POINTCUT DECLARATION WITH @Pointcut
   *    -> reusable
   *    -> one place for definition
   *    
   * COMBINING POINTCUTS: ("expressionOne() && !expressionTwo()") 
   * 
   */
  
  @Pointcut("execution(* de.rieckpil.controllers.*.*(..))")
  public void forControllerMethods() {};
  
  @Before("forControllerMethods()")
  public void logBeforeAllMethodsInPackage() {
    log.info("BEFORE: method in de.rieckpil.controllers.* called at: " + new Date().getTime());
  }
  
  @After("forControllerMethods()")
  public void logAfterAllMethodsInPackage() {
    log.info("AFTER: method in de.rieckpil.controllers.* called at: " + new Date().getTime());
  }

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
