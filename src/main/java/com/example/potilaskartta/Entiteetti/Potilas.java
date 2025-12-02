package com.example.potilaskartta.Entiteetti;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Potilas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;


    private String hetu;
    private String nimi;
    private String sukunimi;
    private String hoitoohje;

    // potilaalla on yksi kiireellisyysluokitus
    @Enumerated(EnumType.STRING)
    private Kiireellisyys kiireellisyys;

    @OneToOne // yksi potilas per paikka
    @JoinColumn(name = "paikka_id")
    private Paikka paikka;


}
