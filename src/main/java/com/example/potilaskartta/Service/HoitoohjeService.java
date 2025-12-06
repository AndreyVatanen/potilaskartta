package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Hoitoohje;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.HoitoohjeRepo;
import com.example.potilaskartta.Repo.PotilasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (hoitoohjeRepo.existsById(hoitoohjeId)) {
            hoitoohjeRepo.deleteById(hoitoohjeId);
            return true;
        }

        return false;
    }



}
