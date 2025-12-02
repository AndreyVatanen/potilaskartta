package com.example.potilaskartta.Controlleri;
import com.example.potilaskartta.Entiteetti.Henkilokunta;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Service.PaivystysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/paivystys")
public class PaivystysControlleri {

    @Autowired
    PaivystysService paivystysService;

    @GetMapping("/ruuhkatilanne")
    public double laskeruuhka(List<Potilas> potilaat, List<Henkilokunta> tyontekijat) {
        return paivystysService.laskeruuhkaTilanne(potilaat,tyontekijat);
    }
}
