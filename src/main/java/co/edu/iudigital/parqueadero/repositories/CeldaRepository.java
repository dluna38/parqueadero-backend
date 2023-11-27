package co.edu.iudigital.parqueadero.repositories;

import co.edu.iudigital.parqueadero.models.Celda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
@Repository
public interface CeldaRepository extends JpaRepository<Celda,Short> {

    Optional<Celda> findByCeldaIgnoreCase(String celda);
    List<Celda> findAllByOcupadaIsTrue();

    List<Celda> findAllByOcupadaIsFalse();
}
