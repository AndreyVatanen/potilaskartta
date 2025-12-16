package com.example.potilaskartta.Repo;

import com.example.potilaskartta.Entiteetti.Kotiutustieto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KotiutustietoRepo extends JpaRepository<Kotiutustieto, Long> {
}
