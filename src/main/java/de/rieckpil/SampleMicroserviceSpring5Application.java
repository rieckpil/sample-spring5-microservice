package de.rieckpil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SampleMicroserviceSpring5Application {

    public static void main(String[] args) {
        SpringApplication.run(SampleMicroserviceSpring5Application.class, args);
    }
    
    @Bean
    public PasswordEncoder passwordEncode() {
      return new BCryptPasswordEncoder();
    }
}
