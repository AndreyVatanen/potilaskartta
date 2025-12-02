package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Henkilokunta;
import com.example.potilaskartta.Repo.HenkilokuntaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HenkilokuntaService {

    @Autowired
    HenkilokuntaRepo henkilokuntaRepo;

    public Henkilokunta luotyontekija(Henkilokunta henkilokunta) {
        return henkilokuntaRepo.save(henkilokunta);

    }

    public Integer tyontekijoidenmaara() {
        List<Henkilokunta> henkilot = henkilokuntaRepo.findAll();
        int maara = henkilot.size();
        return maara;
    }

    public boolean poistaHenkilokunnasta(Long henkilokuntaId) {
        if (henkilokuntaRepo.existsById(henkilokuntaId)) {
            henkilokuntaRepo.deleteById(henkilokuntaId);
            return true;
        }
        return false;
    }

}
