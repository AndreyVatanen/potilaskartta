package com.example.potilaskartta.Controlleri;
import com.example.potilaskartta.Entiteetti.Hoitoohje;
import com.example.potilaskartta.Service.HoitoohjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/hoitoohje")
public class HoitoohjeController {

    @Autowired
    HoitoohjeService hoitoohjeService;

    @PostMapping("/lisaa/{potilasId}")
    public Hoitoohje lisaaHoitoohje(@PathVariable Long potilasId, @RequestBody Hoitoohje hoitoohje) {
        return hoitoohjeService.lisaahoitoOhje(potilasId,hoitoohje);
    }

    @DeleteMapping("/poista/{hoitoOhjeId}")
    public boolean poistaHoitoohje(@PathVariable Long hoitoOhjeId) {
        return hoitoohjeService.poistahoitoOhje(hoitoOhjeId);
    }

    @GetMapping("/hae/hoitoohje/{potilasId}")
    public List<Hoitoohje> haeHoitoohje(@PathVariable Long potilasId) {
        return hoitoohjeService.haehoitoohje(potilasId);
    }

}
