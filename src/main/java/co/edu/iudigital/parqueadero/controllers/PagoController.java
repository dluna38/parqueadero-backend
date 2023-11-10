package co.edu.iudigital.parqueadero.controllers;

import co.edu.iudigital.parqueadero.models.Pago;
import co.edu.iudigital.parqueadero.services.PagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
