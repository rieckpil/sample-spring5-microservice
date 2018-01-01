package de.rieckpil.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private String timezone;
    private String defaultLanguage;
    private String countryCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private List<City> cities;
}
