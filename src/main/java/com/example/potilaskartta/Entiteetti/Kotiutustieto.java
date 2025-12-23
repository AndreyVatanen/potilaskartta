package com.example.potilaskartta.Entiteetti;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Kotiutustieto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    private String tieto;


    @OneToOne
    @JoinColumn(name = "potilas_id", nullable = false, unique = true)
    @JsonBackReference("kotiutusteksti-potilas")
    private Potilas potilas;
}
