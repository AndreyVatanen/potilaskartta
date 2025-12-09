package com.example.potilaskartta.Controlleri;


import com.example.potilaskartta.Entiteetti.Kiireellisyys;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Service.KiireellisyysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/kiireellisyys")
public class KiireellisyysControlleri {


    @Autowired
    KiireellisyysService kiireellisyysService;

    @PutMapping("/muokkaa/{potilasId}")
    public Potilas paivitaKiireellisyys(@PathVariable Long potilasId,@RequestBody Kiireellisyys kiireellisyys) {
        return kiireellisyysService.lisaaKiireellisyys(potilasId,kiireellisyys);
    }
}
