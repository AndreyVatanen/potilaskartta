package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Kiireellisyys;
import com.example.potilaskartta.Entiteetti.Paikka;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.PotilasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotilasService {

    @Autowired
    PotilasRepo potilasRepo;



    public Potilas lisaaPotilas(Potilas potilas) {
        if (potilas.getKiireellisyys() == null) {
            potilas.setKiireellisyys(Kiireellisyys.VIHREA);
        }
        return potilasRepo.save(potilas);
    }

    public void poistaPotilas(Long potilasId) {
        Potilas potilas = potilasRepo.findById(potilasId)
                .orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));

        // irrota paikasta
        if (potilas.getPaikka() != null) {
            Paikka paikka = potilas.getPaikka();
            paikka.setPotilas(null);
            potilas.setPaikka(null);
        }

        // irrota ambulanssista
        if (potilas.getAmbulanssi() != null) {
            potilas.getAmbulanssi().getPotilaat().remove(potilas);
            potilas.setAmbulanssi(null);
        }

        // irrota odotusaulasta
        if (potilas.getOdotusaula() != null) {
            potilas.getOdotusaula().getPotilaat().remove(potilas);
            potilas.setOdotusaula(null);
        }

        // lopuksi poista potilas kokonaan
        potilasRepo.delete(potilas);

    }

    public List<Potilas> NaytaPotilaat() {
        return potilasRepo.findAll();
    }



    public Integer potilaidenMaara() {
        List<Potilas> potilaat = potilasRepo.findAll();
        int maara = potilaat.size();
        return maara;
    }
}
