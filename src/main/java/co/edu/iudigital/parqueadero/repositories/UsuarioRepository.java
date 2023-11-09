package co.edu.iudigital.parqueadero.repositories;

import co.edu.iudigital.parqueadero.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Short> {
    Optional<Usuario> findUsuarioByCorreo(String correo);
}
