package co.edu.iudigital.parqueadero.services;

import co.edu.iudigital.parqueadero.exceptions.FieldRequiredException;
import co.edu.iudigital.parqueadero.exceptions.ResourceNotFoundException;
import co.edu.iudigital.parqueadero.exceptions.ValidationException;
import co.edu.iudigital.parqueadero.models.TipoVehiculo;
import co.edu.iudigital.parqueadero.repositories.TipoVehiculoRepository;
import co.edu.iudigital.parqueadero.repositories.VehiculoRepository;
import co.edu.iudigital.parqueadero.utils.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoVehiculoService {
    private final TipoVehiculoRepository tipoVehiculoRepository;

    @Autowired
    public TipoVehiculoService(TipoVehiculoRepository tipoVehiculoRepository) {
        this.tipoVehiculoRepository = tipoVehiculoRepository;
    }

    public TipoVehiculo saveTipoVehiculo(TipoVehiculo tipoVehiculo) {
        validateTipoVehiculo(tipoVehiculo);
        return tipoVehiculoRepository.save(tipoVehiculo);
    }

    public List<TipoVehiculo> getAllTipoVehiculos() {
        return tipoVehiculoRepository.findAll();
    }

    public TipoVehiculo updateTipoVehiculo(TipoVehiculo tipoVehiculo){
        validateId(tipoVehiculo.getId());
        validateTipoVehiculo(tipoVehiculo);
        Optional<TipoVehiculo> tipoVehiDb = tipoVehiculoRepository.findById(tipoVehiculo.getId());
        tipoVehiDb.orElseThrow(()-> new ResourceNotFoundException("tipo vehiculo")).setTipo(tipoVehiculo.getTipo());
        return tipoVehiculoRepository.save(tipoVehiDb.get());
    }

    public void deleteTipoVehiculo(Short id){
        validateId(id);
        tipoVehiculoRepository.deleteById(id);
    }

    public boolean existById(Short id){
        return tipoVehiculoRepository.existsById(id);
    }
    private void validateTipoVehiculo(TipoVehiculo tipoVehiculo){
        UtilString.validateRequiredField("tipo",tipoVehiculo.getTipo());

        if(tipoVehiculoRepository.existsByTipoContainsIgnoreCase(tipoVehiculo.getTipo())){
            throw new ValidationException("tipo","ya existe");
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
