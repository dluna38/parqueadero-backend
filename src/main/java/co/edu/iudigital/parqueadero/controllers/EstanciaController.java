package co.edu.iudigital.parqueadero.controllers;

import co.edu.iudigital.parqueadero.models.Estancia;
import co.edu.iudigital.parqueadero.services.EstanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/estancia")
public class EstanciaController {
    private final EstanciaService estanciaService;

    @Autowired
    public EstanciaController(EstanciaService estanciaService) {
        this.estanciaService = estanciaService;
    }

    @GetMapping
    public ResponseEntity<Page<Estancia>> getAllEstancias(@RequestParam Map<String,String> paramsEstancia){
        return ResponseEntity.ok(estanciaService.getAllEstancias(paramsEstancia));
    }

    @PostMapping("/entrada")
    public ResponseEntity<Estancia> saveEstanciaEntrada(@RequestBody Estancia estancia){
        return new ResponseEntity<>(estanciaService.saveEstanciaEntrada(estancia), HttpStatus.CREATED);
    }
    @PostMapping("/salida/{id}")
    public ResponseEntity<Estancia> saveEstanciaSalida(@PathVariable Long id){
        return new ResponseEntity<>(estanciaService.saveEstanciaSalida(id), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Estancia> updateEstancia(@RequestBody Estancia estancia){
        return ResponseEntity.ok(estanciaService.updateEstancia(estancia));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstancia(@PathVariable Long id){
        estanciaService.deleteEstancia(id);
        return ResponseEntity.noContent().build();
    }
}
