package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Diagnoosi;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.DiagnoosiRepo;
import com.example.potilaskartta.Repo.PotilasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnoosiService {

    @Autowired
    DiagnoosiRepo diagnoosiRepo;

    @Autowired
    PotilasRepo potilasRepo;


    public Diagnoosi lisaaDiagnoosi(Long potilasId, Diagnoosi diagnoosi) {
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));

        diagnoosi.setPotilas(potilas);
        potilas.getDiagnoosit().add(diagnoosi);

        return diagnoosiRepo.save(diagnoosi);
    }

    // muokattava
    public boolean poistaDiagnoosi(Long diagnoosiId) {
        if (diagnoosiRepo.existsById(diagnoosiId)) {
            diagnoosiRepo.deleteById(diagnoosiId);
            return true;
        }
        return false;
    }


    public List<Diagnoosi> kaikkiDiagnoosit(Long potilasId) {
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));
        List<Diagnoosi> diagnoosit = potilas.getDiagnoosit();
        return diagnoosit;

    }
}
