package com.example.potilaskartta.Repo;

import com.example.potilaskartta.Entiteetti.Paivystys;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaivystysRepo extends JpaRepository<Paivystys, Long> {
}
