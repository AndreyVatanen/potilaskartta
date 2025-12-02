package com.example.potilaskartta.Repo;
import com.example.potilaskartta.Entiteetti.Potilas;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PotilasRepo extends JpaRepository<Potilas, Long> {
}
