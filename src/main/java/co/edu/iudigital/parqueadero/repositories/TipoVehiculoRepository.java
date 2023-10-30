package co.edu.iudigital.parqueadero.repositories;

import co.edu.iudigital.parqueadero.models.TipoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculo,Short> {
    boolean existsById(Short id);
    boolean existsByTipoContainsIgnoreCase(String tipo);
}
