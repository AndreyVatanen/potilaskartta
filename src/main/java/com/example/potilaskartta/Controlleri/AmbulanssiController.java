package com.example.potilaskartta.Controlleri;


import com.example.potilaskartta.Entiteetti.Ambulanssi;
import com.example.potilaskartta.Service.AmbulanssiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ambulanssi")
public class AmbulanssiController {

    @Autowired
    AmbulanssiService ambulanssiService;

    @PostMapping("/lisaa/{potilasId}/{ambulanssiId}")
    public Ambulanssi lisaalistalle(@PathVariable Long potilasId, @PathVariable Long ambulanssiId) {
        return ambulanssiService.lisaaAmbulanssiListaan(potilasId,ambulanssiId);
    }


    @DeleteMapping("/poista/{potilasId}")
    public boolean poistaListalta(@PathVariable Long potilasId) {
        return ambulanssiService.poistaAmbulanssilistalta(potilasId);
    }
}
