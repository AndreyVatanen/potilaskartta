package com.example.potilaskartta.Controlleri;


import com.example.potilaskartta.Entiteetti.Ambulanssi;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Service.AmbulanssiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ambulanssi")
public class AmbulanssiController {

    @Autowired
    AmbulanssiService ambulanssiService;


    @PostMapping("/luo")
    public Ambulanssi luoambulanssiHalli(@RequestBody Ambulanssi ambulanssi) {
        return ambulanssiService.lisaaAmbulanssiHalli(ambulanssi);
    }

    @PostMapping("/lisaa/{potilasId}/{ambulanssiId}")
    public Ambulanssi lisaalistalle(@PathVariable Long potilasId, @PathVariable Long ambulanssiId) {
        return ambulanssiService.lisaaAmbulanssiListaan(potilasId,ambulanssiId);
    }

    @GetMapping("/kaikki/{ambulanssiId}")
    public List<Potilas> naytaKaikki(@PathVariable Long ambulanssiId) {
        return ambulanssiService.naytaKaikki(ambulanssiId);
    }



    @DeleteMapping("/poista/{potilasId}")
    public boolean poistaListalta(@PathVariable Long potilasId) {
        return ambulanssiService.poistaAmbulanssilistalta(potilasId);
    }
}
