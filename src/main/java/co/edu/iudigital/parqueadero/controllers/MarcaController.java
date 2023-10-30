package co.edu.iudigital.parqueadero.controllers;

import co.edu.iudigital.parqueadero.models.Marca;
import co.edu.iudigital.parqueadero.services.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {
    private final MarcaService marcaService;

    @Autowired
    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<Marca>> getAllMarcas(){
        return ResponseEntity.ok(marcaService.getAllMarcas());
    }

    @PostMapping
    public ResponseEntity<Marca> saveMarca(@RequestBody Marca marca){
        return new ResponseEntity<>(marcaService.saveMarca(marca), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Marca> updateMarca(@RequestBody Marca marca){
        return ResponseEntity.ok(marcaService.updateMarca(marca));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable Short id){
        marcaService.deleteMarca(id);
        return ResponseEntity.noContent().build();
    }
}
