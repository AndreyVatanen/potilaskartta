package com.example.potilaskartta.Controlleri;
import com.example.potilaskartta.Entiteetti.Odotusaula;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Service.OdotusaulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/odotusaula")
public class OdotusAulaController {

    @Autowired
    OdotusaulaService odotusaulaService;

    @PostMapping("/lisaa")
    public Odotusaula uusiOdotusAula(@RequestBody Odotusaula odotusaula) {
        return odotusaulaService.luoOodotusAula(odotusaula);
    }

    @PostMapping("/lisaa_aulaan/{potilasId}/{odotusaulaId}")
    public Potilas lisaaPotilasAulaan(@PathVariable Long potilasId,@PathVariable Long odotusaulaId) {
        return odotusaulaService.lisaapotilasAulaListaan(potilasId, odotusaulaId);
    }

    @GetMapping("/kaikki/{odotusaulaId}")
    public List<Potilas> naytaKaikki(@PathVariable Long odotusaulaId) {
        return odotusaulaService.naytaKaikki(odotusaulaId);
    }

    @DeleteMapping("/poista/potilas/{potilasId}")
    public boolean poistaaulasta(@PathVariable Long potilasId) {
        return odotusaulaService.poistaAulasta(potilasId);
    }

    @DeleteMapping("/poista/odotusaula/{odotusaulaId}")
    public boolean poistaOdotusAula(@PathVariable Long odotusaulaId) {
        return odotusaulaService.poistaOodotusaula(odotusaulaId);
    }
}
