package de.rieckpil.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Plant {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private String abbreviation;

  @ManyToOne
  private City city;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "plant")
  private List<Hall> halls;

}
