package com.example.potilaskartta.Repo;

import com.example.potilaskartta.Entiteetti.Laakelista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaakelistaRepo extends JpaRepository<Laakelista, Long> {
}
