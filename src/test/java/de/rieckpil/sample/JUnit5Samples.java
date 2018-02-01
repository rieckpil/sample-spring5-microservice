package de.rieckpil.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import java.time.Duration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("JUnit 5 is awesome 🎉🎉🎉")
public class JUnit5Samples {

  @Test
  @Tag("fast")
  void testMethod() {
    assertEquals(2 + 2, 4);
  }

  @ParameterizedTest
  @ValueSource(strings = {"Hello", "JUnit"})
  void withValueSource(String word) {
    assertNotNull(word);
  }

  @Test
  @Disabled
  @Tag("fast")
  void disabledTestMethod() {
    assertEquals(2 + 2, 4);
  }

  @Test
  @DisplayName("Testing timouts with JUnit 5 🙈")
  public void testDoSomethingTimout() {
    assertTimeout(Duration.ofMillis(1100), () -> {
      doSomething();
    });
  }

  public void doSomething() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
