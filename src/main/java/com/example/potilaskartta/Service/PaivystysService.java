package com.example.potilaskartta.Service;
import com.example.potilaskartta.Entiteetti.Henkilokunta;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Repo.HenkilokuntaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaivystysService {


    public double laskeruuhkaTilanne(List<Potilas> potilaat, List<Henkilokunta> tyontekijat) {
        if (tyontekijat.isEmpty()) return 0;
        double pisteet = potilaat.stream()
                .mapToInt(potilas -> switch (potilas.getKiireellisyys()) {
                    case PUNAINEN -> 3;
                    case KELTAINEN -> 2;
                    case VIHREA -> 1;
                })
                .sum();


        // lasketaan, kuinka monen pisteen verran jokainen hoitaja joutuu hoitamaan
        // 0-1 p / tyontekija ->  ei ruuhkaa
        // 1-2 p / tyontekija -> norm
        // 2-4p  / tyontekejija -> ruuhkautunut
        // >5p / tyontekija -> erittäin ruuhkautunut
        return pisteet / tyontekijat.size();

    }

}
