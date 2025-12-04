package com.example.potilaskartta.Entiteetti;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Diagnoosi {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    private String diagnoosinimi;

    @ManyToOne
    @JoinColumn(name = "potilas_id")
    @JsonBackReference("diag-potilas")
    Potilas potilas;

}
