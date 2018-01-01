package de.rieckpil.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
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
}
