package co.edu.iudigital.parqueadero.repositories;

import co.edu.iudigital.parqueadero.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Short> {

    boolean existsById(Short id);
    boolean existsByNombreContainsIgnoreCase(String nombre);
}
