package co.edu.iudigital.parqueadero.services;

import co.edu.iudigital.parqueadero.controllers.custom.response.PageResponse;
import co.edu.iudigital.parqueadero.exceptions.FieldRequiredException;
import co.edu.iudigital.parqueadero.exceptions.ResourceNotFoundException;
import co.edu.iudigital.parqueadero.exceptions.ValidationException;
import co.edu.iudigital.parqueadero.models.Estancia;
import co.edu.iudigital.parqueadero.repositories.EstanciaRepository;
import co.edu.iudigital.parqueadero.utils.UtilParamEstancia;
import co.edu.iudigital.parqueadero.utils.UtilParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class EstanciaService {
    private final EstanciaRepository estanciaRepository;
    private final VehiculoService vehiculoService;

    private final CeldaService celdaService;
    @Autowired
    public EstanciaService(EstanciaRepository estanciaRepository, VehiculoService vehiculoService, CeldaService celdaService) {
        this.estanciaRepository = estanciaRepository;
        this.vehiculoService = vehiculoService;
        this.celdaService = celdaService;
    }

    @Transactional
    public Estancia saveEstanciaEntrada(Estancia estancia) {
        validateEstancia(estancia);

        if(estancia.getVehiculo().getId() == null){
            estancia.setVehiculo(vehiculoService.saveVehiculo(estancia.getVehiculo()));
        }
        celdaService.ocuparCeldaById(estancia.getCelda().getId());
        return estanciaRepository.save(estancia);
    }
    @Transactional
    public Estancia saveEstanciaSalida(Long id) {
        validateId(id);
        Optional<Estancia> estanciaDB = estanciaRepository.findById(id);
        if(estanciaDB.isEmpty()) throw new ResourceNotFoundException("Estancia");
        if(estanciaDB.get().getFechaSalida() != null) throw new ValidationException("fecha salida","La estancia ya tienen una fecha salida");


        celdaService.desocuparCeldaById(estanciaDB.get().getCelda().getId());
        estanciaDB.get().setFechaSalida(LocalDateTime.now());

        long totalMinutes = ChronoUnit.MINUTES.between(
                estanciaDB.get().getFechaEntrada(),estanciaDB.get().getFechaSalida());

        estanciaDB.get().setMinutosTotales(totalMinutes <= 0 ? 1 : totalMinutes);

        return estanciaRepository.save(estanciaDB.get());
    }


    public PageResponse<Estancia> getAllEstancias(Map<String, String> paramsEstancia) {
        UtilParamEstancia utilParamEstancia = new UtilParamEstancia();

        Map<String,String> equivalenciasSort = Map.of("fe","fechaEntrada","fs","fechaSalida");


        PageRequest pageRequest = UtilParams.getPageRequestFromMapParams(paramsEstancia,
                "fechaEntrada",equivalenciasSort);
        try {
            return new PageResponse<>(estanciaRepository.findAllCustom(utilParamEstancia.getSpecificationFilter(paramsEstancia),pageRequest));
        } catch (PropertyReferenceException e) {
            throw new ValidationException("ordenarPor","No se encontro el campo");
        }
    }


    public void deleteEstancia(Long id){
        validateId(id);
        estanciaRepository.deleteById(id);
    }

    public void validateEstancia(Estancia estancia){
        if(estancia.getVehiculo() == null){
            throw new FieldRequiredException("Vehiculo");
        }
        if(estancia.getCelda() == null){
            throw new FieldRequiredException("Celda");
        }

        if(estancia.getVehiculo().getId() != null) {
            if(!vehiculoService.existById(estancia.getVehiculo().getId())) {
                throw new ResourceNotFoundException("vehiculo");
            }
            Optional<Estancia> estanciaDB = estanciaRepository.findEstanciaByVehiculo_IdAndFechaSalidaIsNull(estancia.getVehiculo().getId());
            if(estanciaDB.isPresent()){
                throw new ValidationException("vehiculo","el vehiculo se encuentra en una estancia sin cerrar, primero cierra la estancia para poder crear una nueva, estancia abierta id: "+estanciaDB.get().getId());
            }
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

    public Optional<Estancia> findById(Long id){
        return estanciaRepository.findById(id);
    }

}
