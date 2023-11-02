package co.edu.iudigital.parqueadero.repositories;

import co.edu.iudigital.parqueadero.models.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo,Long> {

    @Query("select v from Vehiculo v join fetch v.dueno where v.placa = upper(:placa) ")
    Optional<Vehiculo> findByPlacaIgnoreCase(String placa);
    @Query("select v from Vehiculo v join fetch v.dueno where v.dueno.documentoIdentificacion = :documento")
    List<Vehiculo> findAllByDueno_DocumentoIdentificacion(String documento);
}
