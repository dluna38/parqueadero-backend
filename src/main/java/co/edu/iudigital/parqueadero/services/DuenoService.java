package co.edu.iudigital.parqueadero.services;

import co.edu.iudigital.parqueadero.controllers.custom.response.PageResponse;
import co.edu.iudigital.parqueadero.exceptions.FieldRequiredException;
import co.edu.iudigital.parqueadero.exceptions.ResourceNotFoundException;
import co.edu.iudigital.parqueadero.exceptions.ValidationException;
import co.edu.iudigital.parqueadero.models.Dueno;
import co.edu.iudigital.parqueadero.repositories.DuenoRepository;
import co.edu.iudigital.parqueadero.utils.UtilParams;
import co.edu.iudigital.parqueadero.utils.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public PageResponse<Dueno> getAllDuenos(Map<String,String> params) {
        PageRequest pRequest = UtilParams.getPageRequestFromMapParams(params,"id",null);
        return new PageResponse<>(duenoRepository.findAll(pRequest));
    }

    public Dueno updateDueno(Dueno dueno){
        validateId(dueno.getId());
        Optional<Dueno> duenoDB = duenoRepository.findById(dueno.getId());
        if(duenoDB.isEmpty()) throw new ResourceNotFoundException("dueño");

        if(dueno.getNombre()!= null){
            validateNombre(dueno.getNombre());
            duenoDB.get().setNombre(dueno.getNombre());
        }
        if(dueno.getTelefono()!=null){
            validateTelefono(dueno.getTelefono());
            duenoDB.get().setTelefono(dueno.getTelefono());
        }
        if(dueno.getDocumentoIdentificacion() !=null){
            validateDocumento(dueno.getDocumentoIdentificacion());
            duenoDB.get().setDocumentoIdentificacion(dueno.getDocumentoIdentificacion());
        }
        if(dueno.getCorreo() !=null){
            validateCorreo(dueno.getCorreo());
            duenoDB.get().setCorreo(dueno.getCorreo());
        }

        return duenoRepository.save(duenoDB.get());
    }

    public void deleteDueno(Long id){
        validateId(id);
        duenoRepository.deleteById(id);
    }

    private void validateDueno(Dueno dueno){
        validateNombre(dueno.getNombre());
        validateDocumento(dueno.getDocumentoIdentificacion());
        validateTelefono(dueno.getTelefono());
        validateCorreo(dueno.getCorreo());
    }
    private void validateTelefono(String telefono){
        UtilString.validateRequiredField("telefono",telefono);
        if(telefono.length() != 10){
            throw new ValidationException("telefono","no tiene longitud estandar (10)");
        }
    }
    private void validateNombre(String nombre){
        UtilString.validateRequiredField("nombre",nombre);
    }
    private void validateDocumento(String documento){
        UtilString.validateRequiredField("documento",documento);
        if(duenoRepository.existsByDocumentoIdentificacion(documento)){
            throw new ValidationException("documento de identificacion","Ya se encuentra registrado");
        }

    }
    private void validateCorreo(String correo){
        if(correo != null && !UtilString.isEmailValid(correo)){
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
