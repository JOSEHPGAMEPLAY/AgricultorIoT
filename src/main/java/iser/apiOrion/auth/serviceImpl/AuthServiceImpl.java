package iser.apiOrion.auth.serviceImpl;

import iser.apiOrion.auth.dto.LoginDto;
import iser.apiOrion.auth.service.AuthService;
import iser.apiOrion.collection.Usuario;
import iser.apiOrion.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findByUsuario(loginDto.getUsuario());
            if (usuario.isEmpty()) {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }
            if (!JwtTokenProvider.matchPassword(loginDto.getClave(), usuario.get().getPassword())) {
                return new ResponseEntity<>("clave incorrecta", HttpStatus.BAD_REQUEST);
            }
            Map<String, String> response = new HashMap<>();
            response.put("token", jwtTokenProvider.createToken(loginDto));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> register(Usuario usuario, HttpServletRequest request) {
        try {
            String jwt = jwtTokenProvider.resolveToken(request);
            if (jwt == null) {
                return new ResponseEntity<>("Token no valido", HttpStatus.BAD_REQUEST);
            }
            if (this.jwtTokenProvider.getSubject(jwt).equals("ROOT")) {
                Optional<Usuario> usuario1 = usuarioRepository.findByUsuario(usuario.getUsuario());
                if (usuario1.isPresent()) {
                    return new ResponseEntity<>("Usuario ya registrado", HttpStatus.BAD_REQUEST);
                }
                usuario.setPassword(JwtTokenProvider.passwordEncoder(usuario1.get().getPassword()));
                usuarioRepository.save(usuario);
                return new ResponseEntity<>("Usuario registrado", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No tiene permisos para registrar usuario", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
