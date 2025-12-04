package com.example.potilaskartta.Entiteetti;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Ambulanssi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;


    // ambulanssi voi tuoda useita potilaita
    @OneToMany(mappedBy = "ambulanssi")
    private List<Potilas> potilaat;

}
