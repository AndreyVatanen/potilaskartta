package com.example.potilaskartta.Entiteetti;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Paikka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    int paikkanumero;



    @OneToOne(mappedBy = "paikka")
    @JsonIgnore
    Potilas potilas;

    @ManyToOne
    @JoinColumn(name = "paivystys_id")
    Paivystys paivystys;


}
