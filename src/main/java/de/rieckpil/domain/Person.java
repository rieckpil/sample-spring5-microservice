package de.rieckpil.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
public class Person {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;
  private LocalDate dob;
  
  @JsonIgnore
  private String passportId;
  
  @Enumerated
  private Gender gender;
  
}
