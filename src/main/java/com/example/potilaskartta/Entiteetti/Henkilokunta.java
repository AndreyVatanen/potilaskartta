package com.example.potilaskartta.Entiteetti;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Henkilokunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String nimi;
    String sukunimi;
    String rooli; // esim lääkäri, hoitaja yms.


    @ManyToOne
    @JoinColumn(name = "paivystys_id")
    Paivystys paivystys;



}
