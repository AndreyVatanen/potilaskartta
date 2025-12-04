package com.example.potilaskartta.Entiteetti;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Jatkohoito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;


    private String jatkohoito_ohje;

    @ManyToOne
    @JoinColumn(name = "potilas_id")
    @JsonBackReference("jatkohoito-potilas")
    Potilas potilas;

}
