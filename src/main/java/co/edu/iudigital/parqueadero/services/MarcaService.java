package co.edu.iudigital.parqueadero.services;

import co.edu.iudigital.parqueadero.exceptions.FieldRequiredException;
import co.edu.iudigital.parqueadero.exceptions.ResourceNotFoundException;
import co.edu.iudigital.parqueadero.exceptions.ValidationException;
import co.edu.iudigital.parqueadero.models.Marca;
import co.edu.iudigital.parqueadero.repositories.MarcaRepository;
import co.edu.iudigital.parqueadero.utils.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public Marca saveMarca(Marca marca) {
        validateMarca(marca);
        return marcaRepository.save(marca);
    }

    public List<Marca> getAllMarcas() {
        return marcaRepository.findAll();
    }

    public Marca updateMarca(Marca marca){
        validateId(marca.getId());
        validateMarca(marca);
        Optional<Marca> marcaDb = marcaRepository.findById(marca.getId());
        marcaDb.orElseThrow(()-> new ResourceNotFoundException("tipo vehiculo")).setNombre(marca.getNombre());
        return marcaRepository.save(marcaDb.get());
    }

    public void deleteMarca(Short id){
        validateId(id);
        marcaRepository.deleteById(id);
    }

    public boolean existById(Short id){
        return marcaRepository.existsById(id);
    }
    private void validateMarca(Marca marca){
        UtilString.validateRequiredField("nombre",marca.getNombre());

        if(marcaRepository.existsByNombreContainsIgnoreCase(marca.getNombre())){
            throw new ValidationException("nombre","ya existe");
        }
    }
    private void validateId(Short id){
        if(id == null){
            throw new FieldRequiredException("id");
        }
        if(id <=0){
            throw new FieldRequiredException("id negativo");
        }
    }
}
