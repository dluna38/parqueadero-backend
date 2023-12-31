package co.edu.iudigital.parqueadero.services;

import co.edu.iudigital.parqueadero.exceptions.FieldRequiredException;
import co.edu.iudigital.parqueadero.exceptions.ResourceNotFoundException;
import co.edu.iudigital.parqueadero.exceptions.ValidationException;
import co.edu.iudigital.parqueadero.models.Celda;
import co.edu.iudigital.parqueadero.repositories.CeldaRepository;
import co.edu.iudigital.parqueadero.utils.UtilString;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CeldaService {

    private final CeldaRepository celdaRepository;

    public CeldaService(CeldaRepository celdaRepository) {
        this.celdaRepository = celdaRepository;
    }

    public Celda saveCelda(Celda celda){
        validateCelda(celda);
        return celdaRepository.save(celda);
    }

    public void ocuparCeldaById(Short id){
        Celda celdaBD = getCeldaById(id).orElseThrow(() -> new ResourceNotFoundException("celda"));;

        if (celdaBD.isOcupada()) throw new ValidationException("celda","No es posible continuar porque la celda ya esta ocupada");
        //informar que estancia la tiene ocupada????????
        celdaBD.setOcupada(true);
        celdaRepository.save(celdaBD);
    }

    public void desocuparCeldaById(Short id){
        Celda celdaBD = getCeldaById(id).orElseThrow(() -> new ResourceNotFoundException("celda"));;
        if(!celdaBD.isOcupada()) return;
        celdaBD.setOcupada(false);
        celdaRepository.save(celdaBD);
    }

    public Optional<Celda> getCeldaById(Short id){
        if(id == null) throw new ValidationException("id celda","null");
        return celdaRepository.findById(id);
    }
    public Optional<Celda> findById(Short id){
        return celdaRepository.findById(id);
    }
    private void validateCelda(Celda celda) {
        UtilString.validateRequiredField("celda",celda.getCelda());
        if(celdaRepository.findByCeldaIgnoreCase(celda.getCelda()).isPresent()){
            throw new ValidationException("celda","ya existe");
        }
    }
    public Celda updateCelda(Celda celda) {
        if(celda.getId() == null){
            throw new FieldRequiredException("id");
        }
        validateCelda(celda);
        Celda celdaDB = celdaRepository.findById(celda.getId()).orElseThrow(()-> new ResourceNotFoundException("celda"));
        celdaDB.setCelda(celda.getCelda());

        return celdaRepository.save(celdaDB);
    }
    public List<Celda> getAllCeldas() {
        return celdaRepository.findAll();
    }

    public List<Celda> getAllCeldasOcupadas() {
        return celdaRepository.findAllByOcupadaIsTrue();
    }

    public List<Celda> getAllCeldasLibres() {
        return celdaRepository.findAllByOcupadaIsFalse();
    }


    public void deleteCelda(Short id) {
        if(id == null){
            throw new FieldRequiredException("id");
        }
        celdaRepository.deleteById(id);
    }
}
