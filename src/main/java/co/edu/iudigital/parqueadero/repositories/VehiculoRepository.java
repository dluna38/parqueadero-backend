package co.edu.iudigital.parqueadero.repositories;

import co.edu.iudigital.parqueadero.models.Vehiculo;
import co.edu.iudigital.parqueadero.repositories.projections.VehiculoSimple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo,Long> {

    Optional<Vehiculo> findByPlaca(String placa);
    List<Vehiculo> findAllByDueno_DocumentoIdentificacion(String documento);
}
