package co.edu.iudigital.parqueadero.controllers;

import co.edu.iudigital.parqueadero.models.Pago;
import co.edu.iudigital.parqueadero.models.Tarifa;
import co.edu.iudigital.parqueadero.services.TarifaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
