package co.edu.iudigital.parqueadero.services;

import co.edu.iudigital.parqueadero.exceptions.FieldRequiredException;
import co.edu.iudigital.parqueadero.exceptions.ValidationException;
import co.edu.iudigital.parqueadero.models.TipoVehiculo;
import co.edu.iudigital.parqueadero.repositories.TipoVehiculoRepository;
import co.edu.iudigital.parqueadero.repositories.VehiculoRepository;
import co.edu.iudigital.parqueadero.utils.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return tipoVehiculoRepository.save(tipoVehiculo);
    }

    public void deleteTipoVehiculo(Short id){
        validateId(id);
        tipoVehiculoRepository.deleteById(id);
    }

    public boolean existById(Short id){
        return tipoVehiculoRepository.existsById(id);
    }
    private void validateTipoVehiculo(TipoVehiculo tipoVehiculo){
        if(UtilString.stringIsEmptyOrNull(tipoVehiculo.getTipo())){
            throw new FieldRequiredException("tipo vehiculo");
        }
        if(tipoVehiculoRepository.existsByTipoContainsIgnoreCase(tipoVehiculo.getTipo())){
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
