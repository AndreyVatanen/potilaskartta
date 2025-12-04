package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Laakelista;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.LaakelistaRepo;
import com.example.potilaskartta.Repo.PotilasRepo;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaakelistaService {

    @Autowired
    LaakelistaRepo laakelistaRepo;

    @Autowired
    PotilasRepo potilasRepo;


    public Laakelista lisaaLaake(Laakelista laakelista) {
        return laakelistaRepo.save(laakelista);
    }

    public boolean poistaLaake(Long laakeId) {
        if (laakelistaRepo.existsById(laakeId)) {
           laakelistaRepo.deleteById(laakeId);
           return true;
        }
        return false;
    }

    // potilaskohtaiset lääkkeet listana:
    public List<Laakelista> naytaLaakkeet(Long asiakasId) {
        Potilas potilas = potilasRepo.findById(asiakasId).orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));

        // parempi tarkistus tähän, virheenheittoa yms.
        List<Laakelista> laakkeet = potilas.getLaakkeet();
        return laakkeet;

    }
}
