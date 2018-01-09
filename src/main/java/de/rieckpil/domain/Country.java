package de.rieckpil.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;

@Entity
@Data
public class Country {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;
  private double latitude;
  private double longitude;

  @Column(nullable = false)
  private String timezone;

  @Column(nullable = false)
  private String defaultLanguage;

  @Column(unique = true)
  private String countryCode;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
  private List<City> cities;

  @CreationTimestamp
  public Date createdAt;

  @UpdateTimestamp
  private Date lastUpdatedAt;

  @Column(nullable = false)
  private Date validTo =  new Date(4000000000000L);
}
