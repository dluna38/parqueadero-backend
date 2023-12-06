package co.edu.iudigital.parqueadero.controllers;

import co.edu.iudigital.parqueadero.models.Pago;
import co.edu.iudigital.parqueadero.models.Tarifa;
import co.edu.iudigital.parqueadero.services.TarifaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarifa")
public class TarifaController {
    private final TarifaService tarifaService;

    public TarifaController(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
    }

    @PostMapping
    public ResponseEntity<Tarifa> saveTarifa(@RequestBody Tarifa tarifa){
        return new ResponseEntity<>(tarifaService.saveTarifa(tarifa), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Tarifa>> getAllTarifa(){
        return ResponseEntity.ok(tarifaService.getAllTarifas());
    }
}
