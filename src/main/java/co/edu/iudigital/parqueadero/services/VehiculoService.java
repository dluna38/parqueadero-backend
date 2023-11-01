package co.edu.iudigital.parqueadero.services;

import co.edu.iudigital.parqueadero.controllers.custom.response.PageResponse;
import co.edu.iudigital.parqueadero.exceptions.FieldRequiredException;
import co.edu.iudigital.parqueadero.exceptions.ResourceNotFoundException;
import co.edu.iudigital.parqueadero.exceptions.ValidationException;
import co.edu.iudigital.parqueadero.models.Dueno;
import co.edu.iudigital.parqueadero.models.Marca;
import co.edu.iudigital.parqueadero.models.TipoVehiculo;
import co.edu.iudigital.parqueadero.models.Vehiculo;
import co.edu.iudigital.parqueadero.repositories.VehiculoRepository;
import co.edu.iudigital.parqueadero.utils.UtilParams;
import co.edu.iudigital.parqueadero.utils.UtilString;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;
    private final MarcaService marcaService;
    private final TipoVehiculoService tipoVehiculoService;
    private final DuenoService duenoService;
    @Autowired
    public VehiculoService(VehiculoRepository vehiculoRepository, MarcaService marcaService,
                           TipoVehiculoService tipoVehiculoService, DuenoService duenoService) {
        this.vehiculoRepository = vehiculoRepository;
        this.marcaService = marcaService;
        this.tipoVehiculoService = tipoVehiculoService;
        this.duenoService = duenoService;
    }

    public void customJpa(){
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        System.out.println(vehiculos.get(0).getTipoVehiculo().getId());
        System.out.println(vehiculos.get(0).getTipoVehiculo().getTipo());
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
    public PageResponse<Vehiculo> getAllVehiculos(Map<String,String> params) {

        PageRequest pRequest = UtilParams.getPageRequestFromMapParams(params,"id",null);
        return new PageResponse<>(vehiculoRepository.findAll(pRequest));
    }

    public Vehiculo updateVehiculo(Vehiculo vehiculo){
        validateId(vehiculo.getId());
        Optional<Vehiculo> vehiculoDb = vehiculoRepository.findById(vehiculo.getId());
        if(vehiculoDb.isEmpty()) throw new ResourceNotFoundException("vehiculo");

        if(vehiculo.getPlaca()!=null){
            validatePlaca(vehiculo.getPlaca());
            vehiculoDb.get().setPlaca(vehiculo.getPlaca());
        }
        if(vehiculo.getTipoVehiculo()!=null){
            validateTipoVehiculo(vehiculo.getTipoVehiculo());
            vehiculoDb.get().setTipoVehiculo(vehiculo.getTipoVehiculo());
        }
        if(vehiculo.getMarca()!=null){
            validateMarca(vehiculo.getMarca());
            vehiculoDb.get().setMarca(vehiculo.getMarca());
        }
        if(vehiculo.getDueno()!=null){
            validateId(vehiculo.getId());
            validateDueno(vehiculo.getDueno());
            vehiculoDb.get().setDueno(vehiculo.getDueno());
        }

        return vehiculoRepository.save(vehiculoDb.get());
    }

    public void deleteVehiculo(Long id){
        validateId(id);
        vehiculoRepository.deleteById(id);
    }

    public Vehiculo findByPlaca(String placa) {
        return vehiculoRepository.findByPlacaIgnoreCase(placa).orElseThrow(()->new ResourceNotFoundException(("vehiculo")));
    }
    public Vehiculo findById(Long id) {
        return vehiculoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(("vehiculo")));
    }
    public List<Vehiculo> findAllByDocumento(String documento) {
        return vehiculoRepository.findAllByDueno_DocumentoIdentificacion(documento.toUpperCase());
    }

    private void validateVehiculo(Vehiculo vehiculo){
        validatePlaca(vehiculo.getPlaca());
        validateTipoVehiculo(vehiculo.getTipoVehiculo());
        validateMarca(vehiculo.getMarca());
        validateDueno(vehiculo.getDueno());
    }
    private void validateId(Long id){
        if(id == null){
            throw new FieldRequiredException("id");
        }
        if(id <=0){
            throw new FieldRequiredException("id negativo");
        }
    }

    private void validatePlaca(String placa){
        /*//La placas son diferentes entre tipos de vehiculos
        if(!vehiculo.getPlaca().matches("[A-Z]{3}\\d{3}")){

        }*/
        UtilString.validateRequiredField("placa",placa);
        if(vehiculoRepository.findByPlacaIgnoreCase(placa).isPresent()){
            throw new ValidationException("placa","placa ya existe");
        }
    }
    private void validateTipoVehiculo(TipoVehiculo tipoVehiculo){
        if(tipoVehiculo == null) {
            throw new FieldRequiredException("tipo vehiculo");
        }
        if(!tipoVehiculoService.existById(tipoVehiculo.getId())){
            throw new ResourceNotFoundException("tipo vehiculo");
        }
    }
    private void validateMarca(Marca marca){
        if(marca == null) {
            throw new FieldRequiredException("Marca");
        }
        if(!marcaService.existById(marca.getId())){
            throw new ResourceNotFoundException("Marca");
        }
    }
    private void validateDueno(Dueno dueno){
        if(dueno == null){
            throw new FieldRequiredException("Dueño");
        }
        if(dueno.getId() != null && !duenoService.existById(dueno.getId())){
            throw new ResourceNotFoundException("Dueño");
        }
    }

}
