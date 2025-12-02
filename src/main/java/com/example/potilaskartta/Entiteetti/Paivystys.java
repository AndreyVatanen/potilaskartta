package com.example.potilaskartta.Entiteetti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Paivystys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;


    String nimi;
    String osoite;


    // päivystyksessä on useita paikkoja
    @OneToMany(mappedBy = "paivystys")
    @JsonIgnore
    List<Paikka> paikat;


    // päivystyksessä on useita henkilöitä töissä
    @OneToMany(mappedBy = "paivystys")
    @JsonIgnore
    List<Henkilokunta> tyontekijat;


}
