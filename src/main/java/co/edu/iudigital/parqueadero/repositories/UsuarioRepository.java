package co.edu.iudigital.parqueadero.repositories;

import co.edu.iudigital.parqueadero.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Short> {
    Optional<Usuario> findUsuarioByCorreoIgnoreCase(String correo);
    Optional<Usuario> findUsuarioByDocumentoIdentidadIgnoreCase(String documento);
}
