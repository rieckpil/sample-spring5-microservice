package de.rieckpil.aspects;

import java.util.Date;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
@Order(1)
public class ImportantAspect {

  @Pointcut("execution(* de.rieckpil.controllers.*.*(..))")
  public void forControllerMethods() {};
  
  @Before("forControllerMethods()")
  public void logBeforeAllMethodsInPackage() {
    log.info("IMPORTANT!!! BEFORE: method in de.rieckpil.controllers.* called at: " + new Date().getTime());
  }
}
