package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Odotusaula;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.OdotusaulaRepo;
import com.example.potilaskartta.Repo.PotilasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OdotusaulaService {

    @Autowired
    OdotusaulaRepo odotusaulaRepo;

    @Autowired
    PotilasRepo potilasRepo;


    public Potilas lisaapotilasAulaListaan(Long potilasId, Long odotusaulaId) {
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("potilasta ei löytynyt"));
        Odotusaula odotusaula = odotusaulaRepo.findById(odotusaulaId).orElseThrow(() -> new RuntimeException("potilasta ei löytynyt"));
        potilas.setOdotusaula(odotusaula);

        // potilas ei voi olla muissa paikoissa samaan aikaan -> asetetaan null
        potilas.setAmbulanssi(null);
        potilas.setPaikka(null);

        return potilasRepo.save(potilas);

    }

    public boolean poistaAulasta(Long potilasId) {
        if (odotusaulaRepo.existsById(potilasId)) {
            odotusaulaRepo.deleteById(potilasId);
            return true;
        }

        return false;
    }

}
