package com.example.potilaskartta.Controlleri;
import com.example.potilaskartta.Entiteetti.Potilas;
import com.example.potilaskartta.Service.PotilasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/potilas")
public class PotilasControlleri {

   @Autowired
   PotilasService potilasService;

   @PostMapping("/lisaa")
   public Potilas luoPotilas(@RequestBody Potilas potilas) {
        return potilasService.lisaaPotilas(potilas);
   }

   @DeleteMapping("/poista/{potilasId}")
    public void poistaPotilas(@PathVariable Long potilasId) {
      potilasService.poistaPotilas(potilasId);
    }
    @GetMapping("/kaikki")
    public List<Potilas> NaytaPotilaat(){
           return  potilasService.NaytaPotilaat();
    }

    @GetMapping("/maara")
    public Integer potilaidenMaara() {
            return potilasService.potilaidenMaara();
    }

}
