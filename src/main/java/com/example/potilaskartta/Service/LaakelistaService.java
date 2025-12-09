package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Laakelista;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.LaakelistaRepo;
import com.example.potilaskartta.Repo.PotilasRepo;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LaakelistaService {

    @Autowired
    LaakelistaRepo laakelistaRepo;

    @Autowired
    PotilasRepo potilasRepo;


    public Laakelista lisaaLaake(Long potilasId, Laakelista laake) {
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));

        potilas.getLaakkeet().add(laake);
        laake.setPotilas(potilas);

        return laakelistaRepo.save(laake);
    }



    public boolean poistaLaake(Long laakeId) {
        Laakelista laake = laakelistaRepo.findById(laakeId)
                .orElseThrow(() -> new RuntimeException("Lääkettä ei löytynyt"));

        Potilas potilas = laake.getPotilas();


        potilas.getLaakkeet().remove(laake);


        laake.setPotilas(null);


        potilasRepo.save(potilas);


        laakelistaRepo.delete(laake);

        return true;
    }

    // potilaskohtaiset lääkkeet listana:
    public List<Laakelista> naytaLaakkeet(Long potilasId) {
        Potilas potilas = potilasRepo.findById(potilasId).orElseThrow(() -> new RuntimeException("Potilasta ei löytynyt"));

        // parempi tarkistus tähän, virheenheittoa yms.
        List<Laakelista> laakkeet = potilas.getLaakkeet();
        return laakkeet;

    }
}
