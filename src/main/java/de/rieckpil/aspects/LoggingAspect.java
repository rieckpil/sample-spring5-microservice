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

    @Before("execution(public int getAmountOfCountries())")
    public void beforeGetAllCountries() {
        log.info("BEFORE: getAmountOfCountries() called at: " + new Date().getTime());
    }
    
    @After("execution(public int getAmountOfCountries())")
    public void afterGetAllCountries() {
      log.info("AFTER: getAmountOfCountries() called at: " + new Date().getTime());
  }
   
}
