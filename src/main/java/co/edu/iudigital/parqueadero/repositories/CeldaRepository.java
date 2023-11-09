package co.edu.iudigital.parqueadero.repositories;

import co.edu.iudigital.parqueadero.models.Celda;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CeldaRepository extends JpaRepository<Celda,Short> {

    List<Celda> findAllByOcupadaIsTrue();

    List<Celda> findAllByOcupadaIsFalse();
}
