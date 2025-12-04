package com.example.potilaskartta.Entiteetti;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Hoitoohje {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;


    private String ohje;


    @ManyToOne
    @JoinColumn(name = "potilas_id")
    @JsonBackReference("hoitoohje-potilas")
    Potilas potilas;

}
