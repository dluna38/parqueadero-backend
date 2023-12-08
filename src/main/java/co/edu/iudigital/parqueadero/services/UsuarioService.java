package co.edu.iudigital.parqueadero.services;

import co.edu.iudigital.parqueadero.exceptions.ValidationException;
import co.edu.iudigital.parqueadero.models.Usuario;
import co.edu.iudigital.parqueadero.repositories.UsuarioRepository;
import co.edu.iudigital.parqueadero.utils.UtilString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario saveUsuario(Usuario usuario){
        validateUsuario(usuario);
        usuario.setContrasena(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }


    private void validateUsuario(Usuario usuario) {
        UtilString.validateRequiredField("nombre",usuario.getNombre());
        UtilString.validateRequiredField("telefono",usuario.getTelefono());
        UtilString.validateRequiredField("documento",usuario.getDocumentoIdentidad());
        UtilString.validateRequiredField("contraseña",usuario.getPassword());

        if(!UtilString.isEmailValid(usuario.getCorreo())){
            throw new ValidationException("correo","no es un correo valido");
        }
        if(usuarioRepository.findUsuarioByCorreoIgnoreCase(usuario.getCorreo()).isPresent()){
            throw new ValidationException("correo","ya existe");
        }

        if(usuarioRepository.findUsuarioByDocumentoIdentidadIgnoreCase(usuario.getDocumentoIdentidad()).isPresent()){
            throw new ValidationException("Documento de identidad","ya existe");
        }
    }

}
