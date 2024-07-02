package iser.apiOrion.serviceImplement;

import iser.apiOrion.auth.serviceImpl.JwtTokenProvider;
import iser.apiOrion.collection.Usuario;
import iser.apiOrion.collection.Validacion;
import iser.apiOrion.email.service.EmailService;
import iser.apiOrion.repository.UsuarioRepository;
import iser.apiOrion.repository.ValidacionRepository;
import iser.apiOrion.service.ValidacionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static iser.apiOrion.constant.messageConstant.buildMessage;

@Service
public class ValidacionServiceImpl implements ValidacionService {

    /**
     * Repositorio de validacion
     */
    @Autowired
    ValidacionRepository validacionRepository;

    /**
     * Repositorio de usuario
     */
    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * Servicio de email
     */
    @Autowired
    EmailService emailService;

    /**
     * Servicio de jwt
     */
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * Metodo que permite crear un codigo de validacion
     * @param usuario usuario al que se le enviara el codigo
     * @return respuesta de la peticion
     */
    @Override
    public ResponseEntity<?> crearCodigoValidacion(String usuario) {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findByUsuario(usuario);
            if(usuarioOptional.isEmpty()){
                return ResponseEntity.badRequest().body(buildMessage("El usuario no existe"));
            }
            String codigo = String.valueOf((int) (Math.random() * 900000) + 100000);
            Validacion validacion = new Validacion();
            validacion.setUsuario(usuario);
            validacion.setEmail(usuarioOptional.get().getEmail());
            validacion.setCodigo(codigo);
            validacionRepository.save(validacion);
            emailService.sendMail(new String[]{usuarioOptional.get().getEmail()}, "Codigo de validacion", "Su codigo de validacion es: " + codigo);
            return ResponseEntity.ok("{\"message\":\"Se ha enviado un correo con un codigo para recuperar la clave \"}");
        }catch (Exception e){
            System.out.println("ERROR_MESSAGE: " + e.getMessage());
            return ResponseEntity.badRequest().body("ERROR_MESSAGE: " + e.getMessage());
        }
    }

    /**
     * Metodo que permite validar un codigo de validacion
     * @param usuario usuario al que se le enviara el codigo
     * @param codigo codigo de validacion
     * @return respuesta de la peticion
     */
    @Override
    public ResponseEntity<?> validarCodigoValidacion(String usuario, String codigo, HttpServletResponse response, HttpServletRequest request) {
        try {
            Optional<Validacion> validacionOptional = validacionRepository.findByUsuarioAndCodigo(usuario, codigo);
            if (validacionOptional.isEmpty()) {
                return ResponseEntity.badRequest().body(buildMessage("El codigo de validacion es incorrecto"));
            }
            validacionRepository.delete(validacionOptional.get());
            Optional<Usuario> usuarioOptional = usuarioRepository.findByUsuario(usuario);
            if (usuarioOptional.isPresent()) {
                response.addHeader("Authorization", jwtTokenProvider.createToken(usuarioOptional.get().getUsuario(), usuarioOptional.get().getId()));
                response.addHeader("Access-Control-Expose-Headers", "Authorization");
                return ResponseEntity.ok().body(usuarioOptional.get());
            }
            return ResponseEntity.ok("{\"message\":\"No se ha encontrado usuario para retornar\"}");
        } catch (Exception e) {
            System.out.println("ERROR_MESSAGE: " + e.getMessage());
            return ResponseEntity.badRequest().body("ERROR_MESSAGE: " + e.getMessage());
        }
    }
}
