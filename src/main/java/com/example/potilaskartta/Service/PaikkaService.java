package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Paikka;
import com.example.potilaskartta.Entiteetti.Paivystys;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.PaikkaRepo;
import com.example.potilaskartta.Repo.PaivystysRepo;
import com.example.potilaskartta.Repo.PotilasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PaikkaService {

    @Autowired
    PaikkaRepo paikkaRepo;

    @Autowired
    PotilasRepo potilasRepo;

    @Autowired
    PaivystysRepo paivystysRepo;


    public Paikka uusiPaikka(Paikka paikka) {


        return  paikkaRepo.save(paikka);
    }


    
    public Paikka viepotilasPaikalle(Long paikkaId, Long potilasId) {
        Paikka paikka = paikkaRepo.findById(paikkaId).orElseThrow(() -> new RuntimeException("paikkaa ei löytynyt"));
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("potilasta ei löytynyt"));


        paikka.setPotilas(potilas);
        potilas.setPaikka(paikka);

        // poistetaan myös tietokannasta tieto
        if (potilas.getAmbulanssi() != null) {
            potilas.setAmbulanssi(null);
        }

        if (potilas.getOdotusaula() != null){
            potilas.setOdotusaula(null);
        }

        return  paikkaRepo.save(paikka);
    }



    public List<Potilas> naytaosastoPotilaat(Long paivystysId) {


        Paivystys paivystys = paivystysRepo.findById(paivystysId)
                .orElseThrow(() -> new RuntimeException("päivystystä ei löytynyt"));

        List<Potilas> potilaat = new ArrayList<>();


        for (Paikka paikat : paivystys.getPaikat()) {
            if (paikat.getPotilas() != null) {
                potilaat.add(paikat.getPotilas());
            }

        }

        return potilaat;
    }

    public boolean poistaPaikka(Long paikkaId) {
        if (paikkaRepo.existsById(paikkaId)) {
            paikkaRepo.deleteById(paikkaId);
            return true;
        }
        return false;
    }

}
