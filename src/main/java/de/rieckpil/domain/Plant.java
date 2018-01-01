package de.rieckpil.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
