package iser.apiOrion.serviceImplement;


import iser.apiOrion.auth.serviceImpl.JwtTokenProvider;
import iser.apiOrion.collection.Formulario;
import iser.apiOrion.collection.Usuario;
import iser.apiOrion.email.service.EmailService;
import iser.apiOrion.repository.FormularioRepository;
import iser.apiOrion.repository.UsuarioRepository;
import iser.apiOrion.service.FormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static iser.apiOrion.constant.messageConstant.buildMessage;
import static iser.apiOrion.email.EmailConstant.formularioRegistrado;
import static iser.apiOrion.email.EmailConstant.usuarioAceptado;

@Service
public class FormularioServiceImpl implements FormularioService {

    /**
     * Repositorio de formularios
     */
    @Autowired
    FormularioRepository formularioRepository;

    /**
     * Servicio de email
     */
    @Autowired
    EmailService emailService;

    /**
     * Repositorio de usuarios
     */
    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * Metodo que permite buscar todos los formularios
     * @return ResponseEntity con la lista de formularios
     */
    @Override
    public ResponseEntity<?> buscarTodos() {
        try {
            return ResponseEntity.ok(formularioRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(buildMessage("Error al buscar los formularios"));
        }
    }

    /**
     * Metodo que permite buscar un formulario por su id
     * @param id id del formulario a buscar
     * @return ResponseEntity con el formulario encontrado
     */
    @Override
    public ResponseEntity<?> buscarPorId(String id) {
        try {
            return ResponseEntity.ok(formularioRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(buildMessage("Error al buscar el formulario"));
        }
    }

    /**
     * Metodo que permite actualizar un formulario
     * @param formulario formulario a actualizar
     * @return ResponseEntity con el formulario actualizado
     */
    @Override
    public ResponseEntity<?> actualizar(Formulario formulario) {
        try {
            Optional<Formulario> formularioOptional = formularioRepository.findById(formulario.getId());
            if (formularioOptional.isEmpty()) {
                return ResponseEntity.badRequest().body(buildMessage("El formulario no existe"));
            }
            return ResponseEntity.ok(formularioRepository.save(formulario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(buildMessage("Error al actualizar el formulario"));
        }
    }

    /**
     * Metodo que permite borrar un formulario
     * @param id id del formulario a borrar
     * @return ResponseEntity con el resultado de la operacion
     */
    @Override
    public ResponseEntity<?> borrar(String id) {
        try {
            Optional<Formulario> formularioOptional = formularioRepository.findById(id);
            if (formularioOptional.isEmpty()) {
                return ResponseEntity.badRequest().body(buildMessage("El formulario no existe"));
            }
            formularioRepository.deleteById(id);
            return ResponseEntity.ok(buildMessage("Formulario eliminado"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al borrar el formulario");
        }
    }

    /**
     * Metodo que permite crear un formulario
     * @param formulario formulario a crear
     * @return ResponseEntity con el formulario creado
     */
    @Override
    public ResponseEntity<?> crear(Formulario formulario) {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findByUsuario("ROOT");
            Formulario formularioGuardado = formularioRepository.save(formulario);
            if (usuarioOptional.isPresent()) {
                String[] mails = {usuarioOptional.get().getEmail()};
                emailService.sendMail(mails, "Formulario Registrado", formularioRegistrado(formularioGuardado.getNombres()));
            }
            return ResponseEntity.ok(formularioRepository.save(formulario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el formulario");
        }
    }

    /**
     * Metodo que permite aceptar un usuario
     * @param idFormulario id del formulario a aceptar
     * @return ResponseEntity con el resultado de la operacion
     */
    @Override
    public ResponseEntity<?> aceptarUsuario(String idFormulario) {
        try {
            Optional<Formulario> formularioOptional = formularioRepository.findById(idFormulario);
            if (formularioOptional.isEmpty()) {
                return ResponseEntity.badRequest().body(buildMessage("El formulario no existe"));
            }
            Formulario formulario = formularioOptional.get();
            if (usuarioRepository.findByUsuario(formulario.getUsuario()).isPresent()) {
                return ResponseEntity.badRequest().body(buildMessage("El nombre de usuario ya existe"));
            }
            Usuario usuario = new Usuario();
            usuario.setUsuario(formulario.getUsuario());
            usuario.setClave(JwtTokenProvider.passwordEncoder(formulario.getClave()));
            usuario.setNombres(formulario.getNombres());
            usuario.setEmail(formulario.getEmail());
            Usuario usuarioGuardado = usuarioRepository.save(usuario);
            emailService.sendMail(new String[]{usuario.getEmail()}, "Usuario Aceptado", usuarioAceptado());
            return ResponseEntity.ok(usuarioGuardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(buildMessage("Error al aceptar el usuario"));
        }
    }
}
