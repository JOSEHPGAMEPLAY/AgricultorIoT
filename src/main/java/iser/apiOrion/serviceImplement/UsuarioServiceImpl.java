package iser.apiOrion.serviceImplement;


import iser.apiOrion.auth.serviceImpl.JwtTokenProvider;
import iser.apiOrion.collection.Usuario;
import iser.apiOrion.repository.UsuarioRepository;
import iser.apiOrion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static iser.apiOrion.constant.messageConstant.buildMessage;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    /**
     * Repositorio de usuario
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Metodo que permite insertar un usuario
     * @param usuario usuario a insertar
     * @return respuesta de la peticion
     */
    @Override
    public ResponseEntity<?> insertar(Usuario usuario) {
        try {
            String password = JwtTokenProvider.passwordEncoder(usuario.getClave());
            usuario.setClave(password);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite actualizar un usuario
     * @param usuario usuario a actualizar
     * @return respuesta de la peticion
     */
    @Override
    public ResponseEntity<?> actualizar(Usuario usuario) {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getId());
            if(usuarioOptional.isEmpty()){
                return ResponseEntity.badRequest().body(buildMessage("Usuario no encontrado."));
            }
            if(usuario.getClave().isEmpty()){
                usuario.setClave(usuarioOptional.get().getClave());
            }else {
                String clave = JwtTokenProvider.passwordEncoder(usuario.getClave());
                usuario.setClave(clave);
            }
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite eliminar un usuario
     * @param id id del usuario
     * @return respuesta de la peticion
     */
    @Override
    public ResponseEntity<?> eliminar(String id) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            if(usuario.isEmpty()){
                return ResponseEntity.badRequest().body(buildMessage("Usuario no encontrado."));
            }
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite buscar un usuario por su id
     * @param id id del usuario
     * @return usuario
     */
    @Override
    public ResponseEntity<?> buscar(String id) {
        try {
            return ResponseEntity.ok().body(usuarioRepository.findById(id));
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite buscar todos los usuarios
     * @return lista de usuarios
     */
    @Override
    public ResponseEntity<?> buscarTodos() {
        try {
            return ResponseEntity.ok().body(usuarioRepository.findAll());
        } catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
