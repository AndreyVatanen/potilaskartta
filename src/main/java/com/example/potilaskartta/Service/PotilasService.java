package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Paikka;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.OdotusaulaRepo;
import com.example.potilaskartta.Repo.PaikkaRepo;
import com.example.potilaskartta.Repo.PotilasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotilasService {

    @Autowired
    PotilasRepo potilasRepo;



    public Potilas lisaaPotilas(Potilas potilas) {
        return potilasRepo.save(potilas);
    }

    public boolean poistaPotilas(Long potilasId) {
        if (potilasRepo.existsById(potilasId)) {
            potilasRepo.deleteById(potilasId);
            return true;
        }
        return false;
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
