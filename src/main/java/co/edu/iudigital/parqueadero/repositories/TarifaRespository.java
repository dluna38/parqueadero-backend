package co.edu.iudigital.parqueadero.repositories;

import co.edu.iudigital.parqueadero.models.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TarifaRespository extends JpaRepository<Tarifa,Integer> {

    @Query("select t from Tarifa t order by t.fechaImplementacion desc limit 1")
    Optional<Tarifa> findMostRecentTarifa();
}
