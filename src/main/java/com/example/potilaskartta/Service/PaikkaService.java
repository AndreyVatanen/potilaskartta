package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Paikka;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.PaikkaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaikkaService {

    @Autowired
    PaikkaRepo paikkaRepo;


    public Paikka uusiPaikka(Paikka paikka) {
        return  paikkaRepo.save(paikka);
    }

    public Paikka viepotilasPaikalle(Long paikkaId, Potilas potilas) {
        Paikka paikka = paikkaRepo.findById(paikkaId).orElseThrow(() -> new RuntimeException("paikkaa ei löytynyt"));
        paikka.setPotilas(potilas);
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
