package co.edu.iudigital.parqueadero.repositories;

import co.edu.iudigital.parqueadero.models.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DuenoRepository extends JpaRepository<Dueno,Long> {

    boolean existsById(long id);
    boolean existsByDocumentoIdentificacion(String documento);

    Optional<Dueno> findByDocumentoIdentificacion(String documento);
}
