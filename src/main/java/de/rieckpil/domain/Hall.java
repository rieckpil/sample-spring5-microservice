package de.rieckpil.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
}
