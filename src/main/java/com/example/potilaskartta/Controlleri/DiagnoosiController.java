package com.example.potilaskartta.Controlleri;
import com.example.potilaskartta.Entiteetti.Diagnoosi;
import com.example.potilaskartta.Service.DiagnoosiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/diagnoosi")
public class DiagnoosiController {

    @Autowired
    DiagnoosiService diagnoosiService;

    @PostMapping("/lisaa/{potilasId}")
    public Diagnoosi lisaaDiagnoosi(@PathVariable Long potilasId, @RequestBody Diagnoosi diagnoosi) {
        return diagnoosiService.lisaaDiagnoosi(potilasId,diagnoosi);
    }

    @DeleteMapping("/poista/{diagnoosiId}")
    public boolean poistaDiagnoosi(@PathVariable Long diagnoosiId) {
        return diagnoosiService.poistaDiagnoosi(diagnoosiId);
    }

    @GetMapping("/kaikki/{potilasId}")
    public List<Diagnoosi> potilaanDiagnoosit(@PathVariable Long potilasId) {
        return diagnoosiService.kaikkiDiagnoosit(potilasId);
    }
}
