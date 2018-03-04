package de.rieckpil.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Book {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String title;
  private String isbn;
  private int pageCount;
  
  @ManyToOne
  @JoinColumn(name = "author_id", nullable = false, updatable = false)
  private Author author;

}
