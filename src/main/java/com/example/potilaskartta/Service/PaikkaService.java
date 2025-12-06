package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Paikka;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.PaikkaRepo;
import com.example.potilaskartta.Repo.PotilasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaikkaService {

    @Autowired
    PaikkaRepo paikkaRepo;

    @Autowired
    PotilasRepo potilasRepo;


    public Paikka uusiPaikka(Paikka paikka) {
        return  paikkaRepo.save(paikka);
    }


    // potilas viedään paikalle aulasta, tai ennakkoilmoitettujen potilaiden listalta, pitää muuttaa tietokannan toimintaan vielä tuo, että
    // siirron yhteyessä tarkistus kummassa on -> poistetaan joko aulasta tai ambulanssikartalta -> siirto potilaspaikalle.
    public Paikka viepotilasPaikalle(Long paikkaId, Long potilasId) {
        Paikka paikka = paikkaRepo.findById(paikkaId).orElseThrow(() -> new RuntimeException("paikkaa ei löytynyt"));
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("potilasta ei löytynyt"));

        potilas.setOdotusaula(null);
        potilas.setAmbulanssi(null);


        paikka.setPotilas(potilas);
        potilas.getPaikka().setPotilas(potilas);

        return  paikkaRepo.save(paikka);
    }

    public boolean poistaPaikka(Long paikkaId) {
        if (paikkaRepo.existsById(paikkaId)) {
            paikkaRepo.deleteById(paikkaId);
            return true;
        }
        return false;
    }

}
