package co.edu.iudigital.parqueadero.controllers;

import co.edu.iudigital.parqueadero.models.Vehiculo;
import co.edu.iudigital.parqueadero.repositories.VehiculoRepository;
import co.edu.iudigital.parqueadero.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {
    private final VehiculoService vehiculoService;
    private final VehiculoRepository vehiculoRepository;
    @Autowired
    private Environment env;

    @Autowired
    public VehiculoController(VehiculoService vehiculoService, VehiculoRepository vehiculoRepository) {
        this.vehiculoService = vehiculoService;
        this.vehiculoRepository = vehiculoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Vehiculo>> getAllVehiculos(){
        return ResponseEntity.ok(vehiculoService.getAllVehiculos());
    }
    @GetMapping("/placa/{placa}")
    public ResponseEntity<Vehiculo> getFindByPlaca(@PathVariable String placa){
        return ResponseEntity.ok(vehiculoService.findByPlaca(placa));
    }
    @GetMapping("/documento/{documento}")
    public ResponseEntity<List<Vehiculo>> getFindByDocumento(@PathVariable String documento){
        return ResponseEntity.ok(vehiculoService.findAllByDocumento(documento));
    }
    @PostMapping
    public ResponseEntity<Vehiculo> saveVehiculo(@RequestBody Vehiculo vehiculo){
        return new ResponseEntity<>(vehiculoService.saveVehiculo(vehiculo), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Vehiculo> updateVehiculo(@RequestBody Vehiculo vehiculo){
        return ResponseEntity.ok(vehiculoService.updateVehiculo(vehiculo));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable Long id){
        vehiculoService.deleteVehiculo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public String testController(){
        return env.getProperty("TEST_VARIABLE");
    }
}
