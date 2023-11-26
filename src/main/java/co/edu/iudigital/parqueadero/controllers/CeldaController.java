package co.edu.iudigital.parqueadero.controllers;

import co.edu.iudigital.parqueadero.models.Celda;
import co.edu.iudigital.parqueadero.services.CeldaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/celda")
public class CeldaController {
    private final CeldaService celdaService;

    public CeldaController(CeldaService celdaService) {
        this.celdaService = celdaService;
    }

    @PostMapping
    public ResponseEntity<Celda> saveCelda(@RequestBody Celda celda){
        return new ResponseEntity<>(celdaService.saveCelda(celda), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Celda>> getAllCelda(){
        return ResponseEntity.ok(celdaService.getAllCeldas());
    }

    @GetMapping("/ocupadas")
    public ResponseEntity<List<Celda>> getAllCeldasOcupadas(){
        return ResponseEntity.ok(celdaService.getAllCeldasOcupadas());
    }
    @GetMapping("/libres")
    public ResponseEntity<List<Celda>> getAllCeldasLibres(){
        return ResponseEntity.ok(celdaService.getAllCeldasLibres());
    }
    @PutMapping
    public ResponseEntity<Celda> updateCelda(@RequestBody Celda celda){
        return ResponseEntity.ok(celdaService.updateCelda(celda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCelda(@PathVariable Short id){
        celdaService.deleteCelda(id);
        return ResponseEntity.ok().build();
    }
}
