package com.example.potilaskartta.Controlleri;
import com.example.potilaskartta.Entiteetti.Henkilokunta;
import com.example.potilaskartta.Service.HenkilokuntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/henkilokunta")
public class HenkilokuntaControlleri {

    @Autowired
    HenkilokuntaService henkilokuntaService;

    @PostMapping("/luo")
    public Henkilokunta luotyontekija(@RequestBody Henkilokunta henkilokunta) {
        return  henkilokuntaService.luotyontekija(henkilokunta);
    }

    @DeleteMapping("/poista/{tyontekijaId}")
    public boolean poistaTyontekija(@PathVariable Long tyontekijaId) {
        return henkilokuntaService.poistaHenkilokunnasta(tyontekijaId);
    }

    @GetMapping("/maara")
    public Integer tyontekijoidenMaara() {
        return henkilokuntaService.tyontekijoidenmaara();
    }

}
