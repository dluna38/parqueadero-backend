package co.edu.iudigital.parqueadero.controllers;

import co.edu.iudigital.parqueadero.models.Dueno;
import co.edu.iudigital.parqueadero.services.DuenoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dueno")
public class DuenoController {
    private final DuenoService duenoService;

    @Autowired
    public DuenoController(DuenoService duenoService) {
        this.duenoService = duenoService;
    }

    @GetMapping
    public ResponseEntity<List<Dueno>> getAllDuenos(){
        return ResponseEntity.ok(duenoService.getAllDuenos());
    }

    @GetMapping("/documento/{documento}")
    public ResponseEntity<Dueno> getFindByDocumento(@PathVariable String documento){
        return ResponseEntity.ok(duenoService.findByDocumento(documento));
    }

    @PostMapping
    public ResponseEntity<Dueno> saveDueno(@RequestBody Dueno dueno){
        return new ResponseEntity<>(duenoService.saveDueno(dueno), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Dueno> updateDueno(@RequestBody Dueno dueno){
        return ResponseEntity.ok(duenoService.updateDueno(dueno));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDueno(@PathVariable Long id){
        duenoService.deleteDueno(id);
        return ResponseEntity.noContent().build();
    }
}
