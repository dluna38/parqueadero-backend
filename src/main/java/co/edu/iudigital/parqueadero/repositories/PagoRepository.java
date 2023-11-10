package co.edu.iudigital.parqueadero.repositories;

import co.edu.iudigital.parqueadero.models.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago,Long> {

    @Query("select p.id from Pago p where p.estancia.id=:id")
    Long findPagoByEstanciaId(Long id);
}
