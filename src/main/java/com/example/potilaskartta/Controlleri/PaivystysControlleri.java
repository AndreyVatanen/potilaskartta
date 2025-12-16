package com.example.potilaskartta.Controlleri;
import com.example.potilaskartta.Entiteetti.*;
import com.example.potilaskartta.Service.PaivystysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/odotusaulapotilaat_kpl/{odotusaulaId}")
    public int odotusaulamaara(@PathVariable Long odotusaulaId) {
        return paivystysService.laskeodotusAulapotilaat(odotusaulaId);
    }

    @GetMapping("/ambulanssipotilaat_kpl/{ambulanssiId}")
    public int ambulanssimaara(@PathVariable Long ambulanssiId) {
        return paivystysService.laskeAmbulanssipotilaat(ambulanssiId);
    }


    @GetMapping("/osastopotilaat_kpl/{paivystysId}")
    public int osastomaara(@PathVariable Long paivystysId) {
        return paivystysService.laskeosastopotilaat(paivystysId);
    }
}
