package com.example.potilaskartta.Service;

import com.example.potilaskartta.Entiteetti.Kotiutustieto;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.KotiutustietoRepo;
import com.example.potilaskartta.Repo.PotilasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KotiutusTietoService {


    @Autowired
    KotiutustietoRepo kotiutustietoRepo;

    @Autowired
    PotilasRepo potilasRepo;


    public boolean lisaaKotiutustieto(Long potilasId, Kotiutustieto kotiutustieto) {
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));
        kotiutustieto.setPotilas(potilas);
        potilas.setKotiutustieto(kotiutustieto);
        return true;
    }
}
