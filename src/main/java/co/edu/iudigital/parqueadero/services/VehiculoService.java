package co.edu.iudigital.parqueadero.services;

import co.edu.iudigital.parqueadero.exceptions.FieldRequiredException;
import co.edu.iudigital.parqueadero.exceptions.ResourceNotFoundException;
import co.edu.iudigital.parqueadero.models.Vehiculo;
import co.edu.iudigital.parqueadero.repositories.VehiculoRepository;
import co.edu.iudigital.parqueadero.utils.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;
    private final MarcaService marcaService;
    private final TipoVehiculoService tipoVehiculoService;
    private final DuenoService duenoService;

    @Autowired
    public VehiculoService(VehiculoRepository vehiculoRepository, MarcaService marcaService, TipoVehiculoService tipoVehiculoService, DuenoService duenoService) {
        this.vehiculoRepository = vehiculoRepository;
        this.marcaService = marcaService;
        this.tipoVehiculoService = tipoVehiculoService;
        this.duenoService = duenoService;
    }
    @Transactional
    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        validateVehiculo(vehiculo);
        if(vehiculo.getDueno().getId()==null){
            vehiculo.setDueno(duenoService.saveDueno(vehiculo.getDueno()));
        }
        return vehiculoRepository.save(vehiculo);
    }

    public boolean existById(Long id){
        validateId(id);
        return vehiculoRepository.existsById(id);
    }
    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo updateVehiculo(Vehiculo vehiculo){
        validateId(vehiculo.getId());
        validateVehiculo(vehiculo);
        return vehiculoRepository.save(vehiculo);
    }

    public void deleteVehiculo(Long id){
        validateId(id);
        vehiculoRepository.deleteById(id);
    }

    private void validateVehiculo(Vehiculo vehiculo){
        if(UtilString.stringIsEmptyOrNull(vehiculo.getPlaca())){
            throw new FieldRequiredException("placa");
        }

        /*//La placas son diferentes entre tipos de vehiculos
        if(!vehiculo.getPlaca().matches("[A-Z]{3}\\d{3}")){

        }*/
        if(vehiculo.getTipoVehiculo() == null) {
            throw new FieldRequiredException("tipo vehiculo");
        }
        if(!tipoVehiculoService.existById(vehiculo.getTipoVehiculo().getId())){
            throw new ResourceNotFoundException("tipo vehiculo");
        }
        if(vehiculo.getMarca() == null) {
            throw new FieldRequiredException("Marca");
        }
        if(!marcaService.existById(vehiculo.getMarca().getId())){
            throw new ResourceNotFoundException("Marca");
        }
        if(vehiculo.getDueno() == null){
            throw new FieldRequiredException("Dueño");
        }
        if(vehiculo.getDueno().getId() != null && !duenoService.existById(vehiculo.getDueno().getId())){
            throw new ResourceNotFoundException("Dueño");
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

    public Vehiculo findByPlaca(String placa) {
        return vehiculoRepository.findByPlaca(placa).orElseThrow(()->new ResourceNotFoundException(("vehiculo")));
    }
    public Vehiculo findById(Long id) {
        return vehiculoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(("vehiculo")));
    }
    public List<Vehiculo> findAllByDocumento(String documento) {
        return vehiculoRepository.findAllByDueno_DocumentoIdentificacion(documento);
    }
}
