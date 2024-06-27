package iser.apiOrion.auth.serviceImpl;

import iser.apiOrion.auth.dto.LoginDto;
import iser.apiOrion.auth.dto.TokenValidationResult;
import iser.apiOrion.auth.service.AuthService;
import iser.apiOrion.collection.Usuario;
import iser.apiOrion.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static iser.apiOrion.constant.messageConstant.buildMessage;

@Service
public class AuthServiceImpl implements AuthService {

    /**
     * Repositorio de usuarios
     */
    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * Servicio de jwt
     */
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * Metodo que permite loguear un usuario
     * @param loginDto datos del usuario
     * @param response respuesta de la peticion
     * @param request peticion
     * @return respuesta de la peticion
     */
    @Override
    public ResponseEntity<?> login(LoginDto loginDto, HttpServletResponse response, HttpServletRequest request) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findByUsuario(loginDto.getUsuario());
            if (usuario.isEmpty()) {
                return new ResponseEntity<>(buildMessage("usuario no encontrado"), HttpStatus.NOT_FOUND);
            }
            if (!JwtTokenProvider.matchPassword(loginDto.getClave(), usuario.get().getClave())) {
                return new ResponseEntity<>(buildMessage("clave incorrecta"), HttpStatus.BAD_REQUEST);
            }
            response.addHeader("Authorization", jwtTokenProvider.createToken(loginDto.getUsuario(), usuario.get().getId()));
            response.addHeader("Access-Control-Expose-Headers", "Authorization");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("ERROR_MESSAGE: " + e.getMessage());
            System.out.println("ERROR_MESSAGE: " + e.getStackTrace());
            return new ResponseEntity<>(buildMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que permite registrar un usuario
     * @param usuario usuario a registrar
     * @param request peticion
     * @return respuesta de la peticion
     */
    @Override
    public ResponseEntity<?> register(Usuario usuario, HttpServletRequest request) {
        try {
            String jwt = this.jwtTokenProvider.extractToken(request);
            TokenValidationResult validationResult = this.jwtTokenProvider.resolveToken(jwt);
            if (!validationResult.isValid()) {
                return new ResponseEntity<>(validationResult.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            if (this.jwtTokenProvider.getSubject(jwt).equals("ROOT")) {
                Optional<Usuario> usuario1 = usuarioRepository.findByUsuario(usuario.getUsuario());
                if (usuario1.isPresent()) {
                    return new ResponseEntity<>(buildMessage("Usuario ya registrado"), HttpStatus.BAD_REQUEST);
                }
                usuario.setClave(JwtTokenProvider.passwordEncoder(usuario.getClave()));
                usuarioRepository.save(usuario);
                return new ResponseEntity<>(buildMessage("Usuario registrado"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(buildMessage("No tiene permisos para registrar usuario"), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            System.out.println("ERROR_MESSAGE: " + e.getMessage());
            return new ResponseEntity<>(buildMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
