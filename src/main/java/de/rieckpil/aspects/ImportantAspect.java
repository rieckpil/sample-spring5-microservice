package de.rieckpil.aspects;

import java.util.Date;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import de.rieckpil.dtos.CountryDTO;
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

  @AfterReturning(pointcut = "forControllerMethods()", returning = "result")
  public void logAfterAllMethodsInPackage(JoinPoint joinPoint, Object result) {

    if (result instanceof List<?>) {
      List<?> resultList = (List<?>) result;
      log.info(String.format("Length of returned objects: %s", resultList.size()));
    }

    if (result instanceof CountryDTO) {
      CountryDTO resultDTO = (CountryDTO) result;
      resultDTO.setName("Ouch! Name got modified by Aspect x)");
    }

    log.info(result.toString());

    log.info(String.format(
        "\n ===> @AfterReturining: method %s in de.rieckpil.controllers.* called at: %s",
        joinPoint.getSignature().getName(), new Date().getTime()));
  }

  @AfterThrowing(pointcut = "forControllerMethods()", throwing = "theExc")
  public void logAfterThrowingAllMethodsInPackage(JoinPoint joinPoint, Throwable theExc) {

    log.info(String.format("\n xxxx> @AfterThrowing: method '%s' throwed an error: %s <xxxx",
        joinPoint.getSignature().getName(), theExc.getClass().getSimpleName()));
  }
  
  @Around("execution(* de.rieckpil.controllers.CountryController.getAmountOfCountries())")
  public Object afterGetCall(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    
    long begin = System.currentTimeMillis();
    
    Object result = proceedingJoinPoint.proceed();
    
    long end = System.currentTimeMillis();
    
    log.info(String.format("Getting the amount of countries took %s milliseconds", (end-begin)));
    
    return result;
  }
}
