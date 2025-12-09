package com.example.potilaskartta.Controlleri;


import com.example.potilaskartta.Entiteetti.Laakelista;
import com.example.potilaskartta.Service.LaakelistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/laakelista")
public class LaakelistaController {

    @Autowired
    LaakelistaService laakelistaService;

    @PostMapping("/lisaa/{potilasId}")
    public Laakelista lisaaLaake(@PathVariable Long potilasId, @RequestBody Laakelista laakelista) {
        return laakelistaService.lisaaLaake(potilasId,laakelista);
    }

    // korjaa?
    @DeleteMapping("/poista/{laakeId}")
    public boolean poistaLaake(@PathVariable Long laakeId) {
        return laakelistaService.poistaLaake(laakeId);
    }

    @GetMapping("/kaikki/{potilasId}")
    public List<Laakelista> naytapotilaanLaakkeet(@PathVariable Long potilasId) {
        return laakelistaService.naytaLaakkeet(potilasId);
    }
}
