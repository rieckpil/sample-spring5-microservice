package de.rieckpil.controllers;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/effective-java")
public class EffectiveJavaController {

  private Timer primitiveTimer;
  private Timer regularTime;

  public EffectiveJavaController(MeterRegistry registry) {
    this.primitiveTimer = Timer.builder("dps.primitive.timer").description("Counting the time").tags("region", "time").register(registry);
    this.regularTime = Timer.builder("dps.regular.timer").description("Counting the time").tags("region", "time").register(registry);
  }

  @GetMapping("/primitive")
  private Long getMaxIntSumWithPrimitiveLong() throws Exception {

    Long sum = primitiveTimer.recordCallable(() -> {
      Long innerSum = 0L;
      for (int i = 0; i < Integer.MAX_VALUE; i++) {
        innerSum += i;
      }
      return innerSum;
    });


    return sum;

  }

  @GetMapping("/regular")
  private long getMaxIntSumWithRegularLong() throws Exception {

    long sum = regularTime.recordCallable(() -> {
      long innerSum = 0L;
      for (int i = 0; i < Integer.MAX_VALUE; i++) {
        innerSum += i;
      }
      return innerSum;
    });

    return sum;

  }

}
