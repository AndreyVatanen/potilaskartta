package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.*;
import com.example.potilaskartta.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaikkaService {

    @Autowired
    PaikkaRepo paikkaRepo;

    @Autowired
    PotilasRepo potilasRepo;

    @Autowired
    PaivystysRepo paivystysRepo;

    @Autowired
    AmbulanssiRepo ambulanssiRepo;


    @Autowired
    OdotusaulaRepo odotusaulaRepo;

    public Paikka uusiPaikka(Paikka paikka, Long paivystysId) {

        Paivystys paivystys = paivystysRepo.findById(paivystysId).orElseThrow(() -> new RuntimeException("paivystysId"));

        // paikka lisätään tiettyyn päivystykseen.
        paikka.setPaivystys(paivystys);
        paivystys.getPaikat().add(paikka);

        return  paikkaRepo.save(paikka);

    }


    
    public Paikka viepotilasPaikalle(Long paikkaId, Long potilasId) {
        Paikka paikka = paikkaRepo.findById(paikkaId)
                .orElseThrow(() -> new RuntimeException("Paikkaa ei löytynyt"));
        Potilas potilas = potilasRepo.findById(potilasId)
                .orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));


        if (paikka.getPotilas() != null) {
            Potilas entinen = paikka.getPotilas();
            entinen.setPaikka(null);
            potilasRepo.save(entinen);
        }


        if (potilas.getAmbulanssi() != null) {
            Ambulanssi amb = potilas.getAmbulanssi();
            amb.getPotilaat().remove(potilas);
            potilas.setAmbulanssi(null);
            ambulanssiRepo.save(amb);
        }


        if (potilas.getOdotusaula() != null) {
            Odotusaula aula = potilas.getOdotusaula();
            aula.getPotilaat().remove(potilas);
            potilas.setOdotusaula(null);
            odotusaulaRepo.save(aula);
        }


        paikka.setPotilas(potilas);
        potilas.setPaikka(paikka);


        potilasRepo.save(potilas);
        return paikkaRepo.save(paikka);
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
