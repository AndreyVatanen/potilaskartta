package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Ambulanssi;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.AmbulanssiRepo;
import com.example.potilaskartta.Repo.PotilasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmbulanssiService {

    @Autowired
    AmbulanssiRepo ambulanssiRepo;

    @Autowired
    PotilasRepo potilasRepo;

    public Ambulanssi lisaaAmbulanssiListaan(Long potilasId, Long ambulanssiId) {
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("potilasta ei löytynyt"));
        Ambulanssi ambulanssi = ambulanssiRepo.findById(ambulanssiId).orElseThrow(() -> new RuntimeException("ambulanssilistaa ei löytynyt"));

        potilas.setAmbulanssi(ambulanssi);
        ambulanssi.getPotilaat().add(potilas);
        potilas.setPaikka(null);
        potilas.setOdotusaula(null);

        return ambulanssiRepo.save(ambulanssi);

    }

    public boolean poistaAmbulanssilistalta(Long potilasId) {
        if (ambulanssiRepo.existsById(potilasId)) {
            ambulanssiRepo.deleteById(potilasId);
            return true;
        }
        return false;
    }
}
