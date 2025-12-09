package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Kiireellisyys;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.PotilasRepo;
import org.springframework.stereotype.Service;

@Service
public class KiireellisyysService {


    private final PotilasRepo potilasRepo;

    public KiireellisyysService(PotilasRepo potilasRepo) {
        this.potilasRepo = potilasRepo;
    }

    public Potilas lisaaKiireellisyys(Long potilasId, Kiireellisyys kiireellisyys) {
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));
        potilas.setKiireellisyys(kiireellisyys);
        return potilasRepo.save(potilas);
    }
}
