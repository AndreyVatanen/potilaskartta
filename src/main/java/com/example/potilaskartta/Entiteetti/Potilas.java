package com.example.potilaskartta.Entiteetti;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Potilas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;


    private String nimi;
    private String sukunimi;
    private Integer ika;


    // potilaalla on yksi kiireellisyysluokitus
    @Enumerated(EnumType.STRING)
    private Kiireellisyys kiireellisyys;

    @OneToOne // yksi potilas per paikka
    @JoinColumn(name = "paikka_id")
    @JsonManagedReference("paikka-potilas")
    private Paikka paikka;


    @OneToOne // yksi tieto per potilas
    @JoinColumn(name = "kotiutusteksti_id")
    @JsonManagedReference("kotiutusteksti-potilas")
    Kotiutustieto kotiutustieto;


    @ManyToOne
    @JoinColumn(name = "ambulanssi_id")
    @JsonBackReference("ambulanssi-potilas")
    private Ambulanssi ambulanssi;


    @ManyToOne
    @JoinColumn(name = "odotusaula_id")
    @JsonBackReference("aula-potilas")
    private Odotusaula odotusaula;


    // potilaalla voi olla useampi diagnoosi
    @OneToMany(mappedBy = "potilas", cascade = CascadeType.ALL)
    @JsonManagedReference("diag-potilas")
    private List<Diagnoosi> diagnoosit = new ArrayList<>();

    // potilaalla voi olla useampi lääke
    @OneToMany(mappedBy = "potilas", cascade = CascadeType.ALL)
    @JsonManagedReference("laake-potilas")
    private List<Laakelista> laakkeet = new ArrayList<>();

    // potilaalla voi olla useampi eri hoi
    @OneToMany(mappedBy = "potilas", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("hoitoohje-potilas")
    private List<Hoitoohje> hoito_ohje = new ArrayList<>();


    // potilaalla voi olla useampi jatkohoitosuunnitelma
    @OneToMany(mappedBy = "potilas", cascade = CascadeType.ALL)
    @JsonManagedReference("jatkohoito-potilas")
    private List<Jatkohoito> jatko_hoito = new ArrayList<>();

}
