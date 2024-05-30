package iser.apiOrion.serviceImplement;

import iser.apiOrion.collection.Hibernadero;
import iser.apiOrion.collection.UsuarioHibernadero;
import iser.apiOrion.repository.HibernaderoRepository;
import iser.apiOrion.repository.UsuarioHibernaderoRepository;
import iser.apiOrion.service.UsuarioHibernaderoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarionHibernaderoServiceImpl implements UsuarioHibernaderoService {


    /**
     * Repositorio de usuario hibernadero
     */
    @Autowired
    UsuarioHibernaderoRepository usuarioHibernaderoRepository;

    /**
     * Repositorio de hibernadero
     */
    @Autowired
    HibernaderoRepository hibernaderoRepository;

    /**
     * Metodo que permite obtener todos los hibernaderos de un usuario
     * @param id id del usuario
     * @return lista de hibernaderos
     */
    @Override
    public ResponseEntity<?> buscarHibernaderosUsuario(String id) {
        try {
            List<UsuarioHibernadero> usuarioHibernaderos = usuarioHibernaderoRepository.findByIdUsuario(id);

            if (usuarioHibernaderos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            List<String> idHibernaderos = usuarioHibernaderos.stream().map(UsuarioHibernadero::getIdHibernadero).toList();

            List<Hibernadero> hibernaderos = hibernaderoRepository.findAllById(idHibernaderos);

            if (hibernaderos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().body(hibernaderos);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite obtener todos los usuarios de un hibernadero
     * @param id id del hibernadero
     * @return lista de usuarios
     */
    @Override
    public ResponseEntity<?> buscarPorId(String id) {
        try {
            UsuarioHibernadero usuarioHibernadero = usuarioHibernaderoRepository.findById(id).get();
            return ResponseEntity.ok(usuarioHibernadero);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite crear un usuario hibernadero
     * @param idUsuario id del usuario
     * @param idHibernadero id del hibernadero
     * @return usuario hibernadero creado
     */
    @Override
    public ResponseEntity<?> crearUsuarioHibernadero(String idUsuario, String idHibernadero) {
        try {
            UsuarioHibernadero usuarioHibernadero = new UsuarioHibernadero();
            usuarioHibernadero.setIdUsuario(idUsuario);
            usuarioHibernadero.setIdHibernadero(idHibernadero);
            usuarioHibernaderoRepository.save(usuarioHibernadero);
            return ResponseEntity.ok(usuarioHibernadero);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite borrar un usuario hibernadero
     * @param id id del usuario hibernadero
     * @return mensaje de confirmacion
     */
    @Override
    public ResponseEntity<?> borrarUsuarioHibernadero(String id) {
        try {
            usuarioHibernaderoRepository.deleteById(id);
            return ResponseEntity.ok("Usuario Hibernadero borrado");
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite obtener los hibernaderos de un usuario
     * @param idUsuario id del usuario
     * @return lista de hibernaderos
     */
    @Override
    public ResponseEntity<?> buscarPorUsuario(String idUsuario) {
        try {
            List<UsuarioHibernadero> usuarioHibernaderos = usuarioHibernaderoRepository.findByIdUsuario(idUsuario);
            return ResponseEntity.ok(usuarioHibernaderos);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo que permite obtener los usuarios de un hibernadero
     * @param idHibernadero id del hibernadero
     * @return lista de usuarios
     */
    @Override
    public ResponseEntity<?> buscarPorHibernadero(String idHibernadero) {
        try {
            List<UsuarioHibernadero> usuarioHibernaderos = usuarioHibernaderoRepository.findByIdHibernadero(idHibernadero);
            return ResponseEntity.ok(usuarioHibernaderos);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
