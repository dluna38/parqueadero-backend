package co.edu.iudigital.parqueadero.services;

import co.edu.iudigital.parqueadero.controllers.custom.requests.LogInRequest;
import co.edu.iudigital.parqueadero.controllers.custom.response.TokenResponse;
import co.edu.iudigital.parqueadero.models.Usuario;
import co.edu.iudigital.parqueadero.security.jwt.JwtService;
import co.edu.iudigital.parqueadero.utils.UtilString;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsuarioService usuarioService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public TokenResponse logInUsuario(LogInRequest credentials){
        UtilString.validateRequiredField("correo",credentials.correo());
        UtilString.validateRequiredField("contrasena",credentials.contrasena());
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                credentials.correo(),credentials.contrasena()
        ));

        return new TokenResponse(jwtService.generateToken((UserDetails) auth.getPrincipal()));
    }

    public TokenResponse registerUsuario(Usuario usuario){
        return new TokenResponse(jwtService.generateToken(usuarioService.saveUsuario(usuario)));
    }


}
