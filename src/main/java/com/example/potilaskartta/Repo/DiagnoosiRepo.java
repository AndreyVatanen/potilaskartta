package com.example.potilaskartta.Repo;

import com.example.potilaskartta.Entiteetti.Diagnoosi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnoosiRepo extends JpaRepository<Diagnoosi, Long> {
}
