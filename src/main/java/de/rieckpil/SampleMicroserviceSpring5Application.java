package de.rieckpil;

import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
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
    
    @Bean("dataSource")
    @Profile("default")
    @Primary
    public DataSource standaloneDefaultDataSource() {
      return new EmbeddedDatabaseBuilder()
          .setType(EmbeddedDatabaseType.H2)
          .addScript("classpath:scripts/h2-schema.sql")
          .build();
    }
}
