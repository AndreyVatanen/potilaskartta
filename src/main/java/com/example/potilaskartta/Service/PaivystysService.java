package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.*;
import com.example.potilaskartta.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaivystysService {

    @Autowired
   PaivystysRepo paivystysRepo;

    @Autowired
    OdotusaulaRepo odotusaulaRepo;

    @Autowired
    AmbulanssiRepo ambulanssiRepo;


    public Paivystys luoPaivystys(Paivystys paivystys) {
        return paivystysRepo.save(paivystys);
    }

    public boolean poistaPaivystys(Long paivystysId) {
        if (paivystysRepo.existsById(paivystysId)) {
            paivystysRepo.deleteById(paivystysId);
            return true;
        }
        return false;
    }


    public int laskeodotusAulapotilaat(Long odotusaulaId) {
        Odotusaula odotusaula = odotusaulaRepo.findById(odotusaulaId).orElseThrow(() -> new RuntimeException("odotusaulaa ei löytynyt"));
        int maara = odotusaula.getPotilaat().size();
        return maara;
    }


    public int laskeAmbulanssipotilaat(Long ambulanssiId) {
        Ambulanssi ambulanssi = ambulanssiRepo.findById(ambulanssiId).orElseThrow(() -> new RuntimeException("ambulanssia ei löytynyt"));
        int maara = ambulanssi.getPotilaat().size();
        return maara;
    }

    public int laskeosastopotilaat(Long paivystysId) {

        Paivystys paivystys = paivystysRepo.findById(paivystysId).orElseThrow(() -> new RuntimeException("paivystysta"));

        int laskuri = 0;
        for (Paikka paikka : paivystys.getPaikat()) {
            if (paikka.getPotilas() != null) {
                laskuri++;
            }
        }

        return laskuri;
    }
}
