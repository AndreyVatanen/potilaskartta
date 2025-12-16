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


    @OneToOne(mappedBy = "kotiutustieto")
    @JsonBackReference("kotiutustieto-potilas")
    Potilas potilas;
}
