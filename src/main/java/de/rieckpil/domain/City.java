package de.rieckpil.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double latitude;
    private double longitude;

    @ManyToOne
    private Country country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private List<Plant> plants;
}
