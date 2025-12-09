package com.example.potilaskartta.Controlleri;
import com.example.potilaskartta.Entiteetti.Paikka;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Service.PaikkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/paikka")
public class PaikkaControlleri {

    @Autowired
    PaikkaService paikkaService;


    @PostMapping("/luo_paikka/{paivystysId}")
    public Paikka luoPaikka(@RequestBody Paikka paikka, @PathVariable Long paivystysId) {
        return paikkaService.uusiPaikka(paikka, paivystysId);
    }

    @PostMapping("/vie/{paikkaId}/{potilasId}")
    public Paikka viePotilasPaikalle(@PathVariable Long paikkaId, @PathVariable Long potilasId) {
        return paikkaService.viepotilasPaikalle(paikkaId,potilasId);
    }


    @GetMapping("/osastopotilaat/{paivystysId}")
    public List<Potilas> Naytapaikat(@PathVariable Long paivystysId) {
        return paikkaService.naytaosastoPotilaat(paivystysId);
    }


    @DeleteMapping("/poista/{paikkaId}")
    public boolean poistaPaikka(@PathVariable Long paikkaId) {
        return paikkaService.poistaPaikka(paikkaId);
    }
}
