package com.example.potilaskartta.Entiteetti;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Ambulanssi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    private String nimi;

    // ambulanssin tuomia potilaita voi olla monta
    @OneToMany(mappedBy = "ambulanssi")
    @JsonManagedReference("ambulanssi-potilas")
    private List<Potilas> potilaat = new ArrayList<>();

}
