package co.edu.iudigital.parqueadero.services;

import co.edu.iudigital.parqueadero.exceptions.FieldRequiredException;
import co.edu.iudigital.parqueadero.models.Tarifa;
import co.edu.iudigital.parqueadero.repositories.TarifaRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarifaService {
    private final TarifaRespository tarifaRespository;

    public TarifaService(TarifaRespository tarifaRespository) {
        this.tarifaRespository = tarifaRespository;
    }

    public Optional<Tarifa> findById(Integer id){
        return tarifaRespository.findById(id);
    }

    public Optional<Tarifa> findMostRecentTarifa(){
        return tarifaRespository.findMostRecentTarifa();
    }

    public Tarifa saveTarifa(Tarifa tarifa){
        validateTarifa(tarifa);
        return tarifaRespository.save(tarifa);
    }

    private void validateTarifa(Tarifa tarifa) {
        if(tarifa.getTarifaValor() == null){
            throw new FieldRequiredException("tarifa valor");
        }
        if(tarifa.getPorMinutos() == null){
            throw new FieldRequiredException("por minutos");
        }
    }

    public List<Tarifa> getAllTarifas() {
        return tarifaRespository.findAll();
    }
}
