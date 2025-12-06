package com.example.potilaskartta.Controlleri;


import com.example.potilaskartta.Entiteetti.Jatkohoito;
import com.example.potilaskartta.Service.JatkohoitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jatkohoito")
public class JatkohoitoController {


    @Autowired
    JatkohoitoService jatkohoitoService;

    @PostMapping("/lisaa/{potilasId}")
    public Jatkohoito lisaaJatkohoito(@PathVariable Long potilasId, @RequestBody Jatkohoito jatkohoito) {
        return jatkohoitoService.lisaaJatkoihoitoSuunnitelma(potilasId, jatkohoito);
    }

    @DeleteMapping("/poista/{jatkohoitoId}")
    public boolean poistaJatkohoito( @PathVariable Long jatkohoitoId) {
        return jatkohoitoService.poistaJatkohoito(jatkohoitoId);
    }
}
