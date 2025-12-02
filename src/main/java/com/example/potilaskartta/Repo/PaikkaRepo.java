package com.example.potilaskartta.Repo;

import com.example.potilaskartta.Entiteetti.Paikka;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaikkaRepo extends JpaRepository<Paikka, Long> {
}
