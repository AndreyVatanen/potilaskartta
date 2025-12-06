package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Hoitoohje;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.HoitoohjeRepo;
import com.example.potilaskartta.Repo.PotilasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoitoohjeService {

    @Autowired
    PotilasRepo potilasRepo;

    @Autowired
    HoitoohjeRepo hoitoohjeRepo;

    public Hoitoohje lisaahoitoOhje(Long potilasId, Hoitoohje hoitoohje) {
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));

        hoitoohje.setPotilas(potilas);
        potilas.getHoito_ohje().add(hoitoohje);

        return hoitoohjeRepo.save(hoitoohje);

    }

    public boolean poistahoitoOhje(Long hoitoohjeId) {

        Hoitoohje hoitoohje = hoitoohjeRepo.findById(hoitoohjeId)
                .orElseThrow(() -> new RuntimeException("Hoito-ohjetta ei löytynyt"));

        Potilas potilas = hoitoohje.getPotilas();

        // irrota listasta
        potilas.getHoito_ohje().remove(hoitoohje);

        // irrota viite hoito-ohjeelta
        hoitoohje.setPotilas(null);

        // tallenna potilas (päivittää listan)
        potilasRepo.save(potilas);

        // poista hoito-ohje taulusta
        hoitoohjeRepo.delete(hoitoohje);

        return true;
    }

}
