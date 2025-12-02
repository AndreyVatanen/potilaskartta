package com.example.potilaskartta.Controlleri;
import com.example.potilaskartta.Entiteetti.Paikka;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Service.PaikkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paikka")
public class PaikkaControlleri {

    @Autowired
    PaikkaService paikkaService;


    @PostMapping("/luo")
    public Paikka luoPaikka(@RequestBody Paikka paikka) {
        return paikkaService.uusiPaikka(paikka);
    }

    @PostMapping("/vie/{paikkaId}")
    public Paikka viePotilasPaikalle(@PathVariable Long paikkaId, @RequestBody Potilas potilas) {
        return paikkaService.viepotilasPaikalle(paikkaId,potilas);
    }


    @DeleteMapping("/poista/{paikkaId}")
    public boolean poistaPaikka(@PathVariable Long paikkaId) {
        return paikkaService.poistaPaikka(paikkaId);
    }
}
