package co.edu.iudigital.parqueadero.services;

import co.edu.iudigital.parqueadero.exceptions.FieldRequiredException;
import co.edu.iudigital.parqueadero.exceptions.ResourceNotFoundException;
import co.edu.iudigital.parqueadero.exceptions.ValidationException;
import co.edu.iudigital.parqueadero.models.Dueno;
import co.edu.iudigital.parqueadero.repositories.DuenoRepository;
import co.edu.iudigital.parqueadero.utils.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuenoService {
    private final DuenoRepository duenoRepository;

    @Autowired
    public DuenoService(DuenoRepository duenoRepository) {
        this.duenoRepository = duenoRepository;
    }

    public Dueno saveDueno(Dueno dueno) {
        validateDueno(dueno);
        return duenoRepository.save(dueno);
    }

    public List<Dueno> getAllDuenos() {
        return duenoRepository.findAll();
    }

    public Dueno updateDueno(Dueno dueno){
        validateId(dueno.getId());
        validateDueno(dueno);
        return duenoRepository.save(dueno);
    }

    public void deleteDueno(Long id){
        validateId(id);
        duenoRepository.deleteById(id);
    }

    private void validateDueno(Dueno dueno){
        if(UtilString.stringIsEmptyOrNull(dueno.getNombre())){
            throw new FieldRequiredException("nombre");
        }
        if(UtilString.stringIsEmptyOrNull(dueno.getDocumentoIdentificacion())){
            throw new FieldRequiredException("documento de identificacion");
        }
        if(duenoRepository.existsByDocumentoIdentificacion(dueno.getDocumentoIdentificacion())){
            throw new ValidationException("documento de identificacion","Ya se encuentra registrado");
        }
        if(UtilString.stringIsEmptyOrNull(dueno.getTelefono())){
            throw new FieldRequiredException("telefono");
        }
        if(dueno.getTelefono().length() != 10){
            throw new ValidationException("telefono","no tiene longitud estandar (10)");
        }
        if(dueno.getCorreo() != null && !UtilString.isEmailValid(dueno.getCorreo())){
           throw new ValidationException("correo","No es valido");
        }
    }
    private void validateId(Long id){
        if(id == null){
            throw new FieldRequiredException("id");
        }
        if(id <=0){
            throw new FieldRequiredException("id negativo");
        }
    }

    public boolean existById(Long id){
        return duenoRepository.existsById(id);
    }
    public boolean existByDocumento(String documento){
        return duenoRepository.existsByDocumentoIdentificacion(documento);
    }
    public Dueno findByDocumento(String documento){
        return duenoRepository.findByDocumentoIdentificacion(documento).orElseThrow(()-> new ResourceNotFoundException("dueño"));
    }
}
