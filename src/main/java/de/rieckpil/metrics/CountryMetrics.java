package de.rieckpil.metrics;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import de.rieckpil.domain.Country;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class CountryMetrics {

  private Counter countryCounter;

  public CountryMetrics(MeterRegistry meterRegistry) {
    this.countryCounter = meterRegistry.counter("country_counter");
  }

  @EventListener
  public void handleOrderCreatedEvent(Country country) {
    System.out.println("Country created");
    countryCounter.increment();
  }

}
