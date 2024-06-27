package iser.apiOrion.auth.controller;


import iser.apiOrion.auth.dto.LoginDto;
import iser.apiOrion.auth.service.AuthService;
import iser.apiOrion.collection.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Inicio de sesion
     * @param loginDto datos de inicio de sesion
     * @param response respuesta http
     * @param request peticion http
     * @return respuesta de la peticion
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpServletResponse response, HttpServletRequest request) {

        return this.authService.login(loginDto, response, request);
    }

    /**
     * Registro de usuario
     * @param usuario usuario a registrar
     * @param request peticion http
     * @return respuesta de la peticion
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario, HttpServletRequest request) {
        return this.authService.register(usuario, request);
    }

}
