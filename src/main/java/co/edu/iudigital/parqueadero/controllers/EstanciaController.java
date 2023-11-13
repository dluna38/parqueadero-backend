package co.edu.iudigital.parqueadero.controllers;

import co.edu.iudigital.parqueadero.controllers.custom.response.PageResponse;
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
    public ResponseEntity<PageResponse<Estancia>> getAllEstancias(@RequestParam(required = false) Map<String,String> paramsEstancia){
        return ResponseEntity.ok(estanciaService.getAllEstancias(paramsEstancia));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PageResponse<Estancia>> getDetailEstancia(@PathVariable Long id){
        return ResponseEntity.ok(estanciaService.getDetailEstancia(id));
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
    public ResponseEntity<String> updateEstancia(@RequestBody Estancia estancia){
        return new ResponseEntity<>("No se soporta la operación",HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstancia(@PathVariable Long id){
        estanciaService.deleteEstancia(id);
        return ResponseEntity.noContent().build();
    }
}
