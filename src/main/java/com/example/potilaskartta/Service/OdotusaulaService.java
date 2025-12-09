package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Ambulanssi;
import com.example.potilaskartta.Entiteetti.Odotusaula;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.AmbulanssiRepo;
import com.example.potilaskartta.Repo.OdotusaulaRepo;
import com.example.potilaskartta.Repo.PotilasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OdotusaulaService {

    @Autowired
    OdotusaulaRepo odotusaulaRepo;

    @Autowired
    PotilasRepo potilasRepo;




    public Odotusaula luoOodotusAula(Odotusaula odotusaula) {
        return odotusaulaRepo.save(odotusaula);
    }



    public Potilas lisaapotilasAulaListaan(Long potilasId, Long odotusaulaId) {
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("potilasta ei löytynyt"));
        Odotusaula odotusaula = odotusaulaRepo.findById(odotusaulaId).orElseThrow(() -> new RuntimeException("potilasta ei löytynyt"));


        potilas.setOdotusaula(odotusaula);
        odotusaula.getPotilaat().add(potilas);

        // potilas ei voi olla muissa paikoissa samaan aikaan -> asetetaan null
        potilas.setAmbulanssi(null);
        potilas.setPaikka(null);



        return potilasRepo.save(potilas);

    }


    public List<Potilas> naytaKaikki(Long odotusaulaId) {

        Odotusaula odotusaula = odotusaulaRepo.findById(odotusaulaId).orElseThrow(() -> new RuntimeException("odotusaulaa ei löytynyt"));
        return odotusaula.getPotilaat();
    }

    public boolean poistaAulasta(Long potilasId) {
        if (odotusaulaRepo.existsById(potilasId)) {
            odotusaulaRepo.deleteById(potilasId);
            return true;
        }

        return false;
    }


    public boolean poistaOodotusaula(Long odotusaulaId) {
        if (odotusaulaRepo.existsById(odotusaulaId)) {
            odotusaulaRepo.deleteById(odotusaulaId);
            return true;
        }
        return false;
    }
}
