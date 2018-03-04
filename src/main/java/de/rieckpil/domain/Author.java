package de.rieckpil.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Author {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  
  private String lastName;
  private String firstName;

}
