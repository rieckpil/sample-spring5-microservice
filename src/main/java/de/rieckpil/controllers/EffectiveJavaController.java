package de.rieckpil.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/effective-java")
public class EffectiveJavaController {

  @GetMapping("/primitive")
  private Long getMaxIntSumWithPrimitiveLong() {

    Long sum = 0L;

    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      sum += i;
    }

    return sum;

  }

  @GetMapping("/regular")
  private long getMaxIntSumWithRegularLong() {

    long sum = 0;

    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      sum += i;
    }

    return sum;

  }

}
