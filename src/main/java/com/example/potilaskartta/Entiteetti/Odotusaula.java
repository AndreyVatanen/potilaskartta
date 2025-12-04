package com.example.potilaskartta.Entiteetti;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Odotusaula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @OneToMany(mappedBy = "odotusaula")
    private List<Potilas> potilaat;
}
