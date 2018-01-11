package de.rieckpil.aspects;

import java.util.Date;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
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
  public void logBeforeAllMethodsInPackage(JoinPoint joinPoint) {

    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    
    Object[] args = joinPoint.getArgs();
    
    for (Object argument : args) {
      log.info(String.format("argument passed: '%s'", argument));
    }

    log.info(
        String.format("IMPORTANT!!! BEFORE: method %s in de.rieckpil.controllers.* called at: %s",
            methodSignature, new Date().getTime()));
  }
}
