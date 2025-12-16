package com.example.potilaskartta.Controlleri;
import com.example.potilaskartta.Entiteetti.Kotiutustieto;
import com.example.potilaskartta.Service.KotiutusTietoService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/kotiutustieto")
public class KotiutusTietoController {

    KotiutusTietoService kotiutusTietoService;

    @PostMapping("/lisaa/{potilasId}")
    public boolean lisaaKotiutustieto(@PathVariable Long potilasId, @RequestBody Kotiutustieto kotiutustieto) {
        return kotiutusTietoService.lisaaKotiutustieto(potilasId,kotiutustieto);
    }



}
