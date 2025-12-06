package com.example.potilaskartta.Entiteetti;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Odotusaula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    private String nimi;

    @OneToMany(mappedBy = "odotusaula")
    @JsonManagedReference("aula-potilas")
    private List<Potilas> potilaat = new ArrayList<>();
}
