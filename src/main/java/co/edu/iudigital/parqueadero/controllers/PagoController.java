package co.edu.iudigital.parqueadero.controllers;

import co.edu.iudigital.parqueadero.models.Pago;
import co.edu.iudigital.parqueadero.services.PagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pago")
public class PagoController {
    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    public ResponseEntity<Pago> generatePago(@RequestBody Pago pago){
        return new ResponseEntity<>(pagoService.savePago(pago), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pago> getPagoByEstanciaId(@PathVariable Long id){
        return ResponseEntity.ok(pagoService.getPagoEstanciaById(id));
    }
    @GetMapping
    public ResponseEntity<List<Pago>> getAllPagos(){
        return ResponseEntity.ok(pagoService.getAllPago());
    }

}
