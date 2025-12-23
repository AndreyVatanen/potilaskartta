package com.example.potilaskartta.Controlleri;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Service.PotilasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/kotiutustieto")
public class KotiutusTietoController {



    @Autowired
    PotilasService potilasService;

    @PostMapping("/{id}/kotiuta")
    public Potilas kotiuta(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        return potilasService.lisaaKotiutustieto(id, body.get("tieto"));
    }

}
