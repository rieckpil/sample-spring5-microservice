package de.rieckpil.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;

@Entity
@Data
public class Hall {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private String description;

  @ManyToOne
  private Plant plant;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "hall")
  private List<Machine> machines;

  @CreationTimestamp
  public Date createdAt;

  @UpdateTimestamp
  private Date lastUpdatedAt;
}
