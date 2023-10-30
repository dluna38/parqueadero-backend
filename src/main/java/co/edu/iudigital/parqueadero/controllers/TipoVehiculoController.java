package co.edu.iudigital.parqueadero.controllers;

import co.edu.iudigital.parqueadero.models.TipoVehiculo;
import co.edu.iudigital.parqueadero.services.TipoVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tvehiculo")
public class TipoVehiculoController {
    private final TipoVehiculoService marcaService;

    @Autowired
    public TipoVehiculoController(TipoVehiculoService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<TipoVehiculo>> getAllTipoVehiculos(){
        return ResponseEntity.ok(marcaService.getAllTipoVehiculos());
    }

    @PostMapping
    public ResponseEntity<TipoVehiculo> saveTipoVehiculo(@RequestBody TipoVehiculo marca){
        return new ResponseEntity<>(marcaService.saveTipoVehiculo(marca), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<TipoVehiculo> updateTipoVehiculo(@RequestBody TipoVehiculo marca){
        return ResponseEntity.ok(marcaService.updateTipoVehiculo(marca));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoVehiculo(@PathVariable Short id){
        marcaService.deleteTipoVehiculo(id);
        return ResponseEntity.noContent().build();
    }
}
