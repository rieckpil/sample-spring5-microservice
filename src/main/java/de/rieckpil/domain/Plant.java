package de.rieckpil.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
