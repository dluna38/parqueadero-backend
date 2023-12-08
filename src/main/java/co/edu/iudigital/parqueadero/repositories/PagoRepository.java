package co.edu.iudigital.parqueadero.repositories;

import co.edu.iudigital.parqueadero.models.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PagoRepository extends JpaRepository<Pago,Long> {

    @Query("select p from Pago p where p.estancia.id=:id")
    Optional<Pago> findPagoByEstanciaId(Long id);

    @Query("select p from Pago p join fetch p.estancia e join fetch e.vehiculo v join fetch v.dueno where p.id=:id")
    Optional<Pago> findDetailPagoById(Long id);

}
