package com.example.potilaskartta.Controlleri;


import com.example.potilaskartta.Entiteetti.Laakelista;
import com.example.potilaskartta.Service.LaakelistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public boolean poistaLaake(Long laakeId) {
        return laakelistaService.poistaLaake(laakeId);
    }
}
