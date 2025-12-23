package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.*;
import com.example.potilaskartta.Repo.PotilasRepo;
import jakarta.transaction.Transactional;
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
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));

        if (potilas.getTila() == PotilasTila.KOTIUTETTU) {
            throw new IllegalStateException("Kotiutettua potilasta ei saa poistaa");
        }

        if (potilas.getPaikka() != null) {
            potilas.getPaikka().setPotilas(null);
            potilas.setPaikka(null);
        }

        if (potilas.getAmbulanssi() != null) {
            potilas.getAmbulanssi().getPotilaat().remove(potilas);
            potilas.setAmbulanssi(null);
        }

        if (potilas.getOdotusaula() != null) {
            potilas.getOdotusaula().getPotilaat().remove(potilas);
            potilas.setOdotusaula(null);
        }

        potilasRepo.delete(potilas);

    }



    @Transactional
    public Potilas lisaaKotiutustieto(Long potilasId, String yhteenveto) {

        Potilas potilas = potilasRepo.findById(potilasId)
                .orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));

        if (potilas.getTila() == PotilasTila.KOTIUTETTU) {
            throw new IllegalStateException("Potilas on jo kotiutettu");
        }


        Kotiutustieto kt = new Kotiutustieto();
        kt.setTieto(yhteenveto);
        kt.setPotilas(potilas);
        potilas.setKotiutustieto(kt);


        if (potilas.getPaikka() != null) {
            potilas.getPaikka().setPotilas(null);
            potilas.setPaikka(null);
        }

        if (potilas.getAmbulanssi() != null) {
            potilas.getAmbulanssi().getPotilaat().remove(potilas);
            potilas.setAmbulanssi(null);
        }

        if (potilas.getOdotusaula() != null) {
            potilas.getOdotusaula().getPotilaat().remove(potilas);
            potilas.setOdotusaula(null);
        }


        potilas.setTila(PotilasTila.KOTIUTETTU);


        return potilasRepo.save(potilas);
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
