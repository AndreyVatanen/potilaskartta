package com.example.potilaskartta.Service;


import com.example.potilaskartta.Entiteetti.Hoitoohje;
import com.example.potilaskartta.Entiteetti.Jatkohoito;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.JatkohoitoRepo;
import com.example.potilaskartta.Repo.PotilasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JatkohoitoService {

    @Autowired
    JatkohoitoRepo jatkohoitoRepo;

    @Autowired
    PotilasRepo potilasRepo;

    public Jatkohoito lisaaJatkoihoitoSuunnitelma(Long potilasId, Jatkohoito jatkohoito) {
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));

        jatkohoito.setPotilas(potilas);
        potilas.getJatko_hoito().add(jatkohoito);

        return jatkohoitoRepo.save(jatkohoito);

    }



    public boolean poistaJatkohoito(Long jatkohoitoId) {

        Jatkohoito jatkohoito = jatkohoitoRepo.findById(jatkohoitoId)
                .orElseThrow(() -> new RuntimeException("Hoito-ohjetta ei löytynyt"));
        Potilas potilas = jatkohoito.getPotilas();

        potilas.getJatko_hoito().remove(jatkohoito);
        jatkohoito.setPotilas(null);

        potilasRepo.save(potilas);
        jatkohoitoRepo.delete(jatkohoito);
        return false;

    }


    public List<Jatkohoito> haeJatkohoito(Long potilasId) {
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));
        List<Jatkohoito> jatkohoidot = potilas.getJatko_hoito();
        return jatkohoidot;
    }
}
