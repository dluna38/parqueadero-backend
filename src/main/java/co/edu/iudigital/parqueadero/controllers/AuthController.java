package co.edu.iudigital.parqueadero.controllers;

import co.edu.iudigital.parqueadero.controllers.custom.requests.LogInRequest;
import co.edu.iudigital.parqueadero.controllers.custom.response.TokenResponse;
import co.edu.iudigital.parqueadero.models.Usuario;
import co.edu.iudigital.parqueadero.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> registerUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(authService.registerUsuario(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> logInUsuario(@RequestBody LogInRequest logInRequest){
        return ResponseEntity.ok(authService.logInUsuario(logInRequest));
    }
}
