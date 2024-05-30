package iser.apiOrion.serviceImplement;

import iser.apiOrion.collection.Usuario;
import iser.apiOrion.collection.Validacion;
import iser.apiOrion.email.service.EmailService;
import iser.apiOrion.repository.UsuarioRepository;
import iser.apiOrion.repository.ValidacionRepository;
import iser.apiOrion.service.ValidacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidacionServiceImpl implements ValidacionService {

    @Autowired
    ValidacionRepository validacionRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EmailService emailService;

    @Override
    public ResponseEntity<?> crearCodigoValidacion(String usuario) {
        try {

            Optional<Usuario> usuarioOptional = usuarioRepository.findByUsuario(usuario);

            if(usuarioOptional.isEmpty()){
                return ResponseEntity.badRequest().body("El usuario no existe");
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

    @Override
    public ResponseEntity<?> validarCodigoValidacion(String usuario, String codigo) {
        try {
            Optional<Validacion> validacionOptional = validacionRepository.findByUsuarioAndCodigo(usuario, codigo);

            if (validacionOptional.isEmpty()) {
                return ResponseEntity.badRequest().body("El codigo de validacion es incorrecto");
            }

            validacionRepository.delete(validacionOptional.get());

            return ResponseEntity.ok("{\"message\":\"Codigo de validacion correcto\"}");
        } catch (Exception e) {
            System.out.println("ERROR_MESSAGE: " + e.getMessage());
            return ResponseEntity.badRequest().body("ERROR_MESSAGE: " + e.getMessage());
        }
    }
}
