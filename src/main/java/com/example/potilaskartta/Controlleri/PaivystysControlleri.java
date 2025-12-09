package com.example.potilaskartta.Controlleri;
import com.example.potilaskartta.Entiteetti.Henkilokunta;
import com.example.potilaskartta.Entiteetti.Paikka;
import com.example.potilaskartta.Entiteetti.Paivystys;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Service.PaivystysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/paivystys")
public class PaivystysControlleri {

    @Autowired
    PaivystysService paivystysService;

    @PostMapping("/luo")
    public Paivystys uusipaivystys(@RequestBody Paivystys paivystys) {
        return paivystysService.luoPaivystys(paivystys);
    }

    @DeleteMapping("/poista/{paivystysId}")
    public boolean poistaPaivystys(@PathVariable Long paivystysId) {
        return paivystysService.poistaPaivystys(paivystysId);
    }



    @GetMapping("/ruuhkatilanne")
    public double laskeruuhka(List<Potilas> potilaat, List<Henkilokunta> tyontekijat) {
        return paivystysService.laskeruuhkaTilanne(potilaat,tyontekijat);
    }
}
