package com.example.potilaskartta.Entiteetti;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
public class Laakelista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;


    // myöhemmin vahvuus, maara yms
    private String nimi;


    @ManyToOne
    @JoinColumn(name = "potilas_id")
    @JsonBackReference("laake-potilas")
    Potilas potilas;

}
