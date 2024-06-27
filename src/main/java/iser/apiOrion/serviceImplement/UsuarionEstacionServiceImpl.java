package iser.apiOrion.serviceImplement;

import iser.apiOrion.collection.Estacion;
import iser.apiOrion.collection.UsuarioEstacion;
import iser.apiOrion.repository.EstacionRepository;
import iser.apiOrion.repository.UsuarioEstacionRepository;
import iser.apiOrion.service.UsuarioEstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarionEstacionServiceImpl implements UsuarioEstacionService {


    /**
     * Repositorio de usuario hibernadero
     */
    @Autowired
    UsuarioEstacionRepository usuarioEstacionRepository;

    /**
     * Repositorio de hibernadero
     */
    @Autowired
    EstacionRepository estacionRepository;

    /**
     * Metodo que permite obtener todos los hibernaderos de un usuario
     * @param id id del usuario
     * @return lista de hibernaderos
     */
    @Override
    public ResponseEntity<?> buscarHibernaderosUsuario(String id) {
        try {
            List<UsuarioEstacion> usuarioEstacions = usuarioEstacionRepository.findByIdUsuario(id);

            if (usuarioEstacions.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            List<String> idHibernaderos = usuarioEstacions.stream().map(UsuarioEstacion::getIdHibernadero).toList();

            List<Estacion> hibernaderos = estacionRepository.findAllById(idHibernaderos);

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
            UsuarioEstacion usuarioEstacion = usuarioEstacionRepository.findById(id).get();
            return ResponseEntity.ok(usuarioEstacion);
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
            UsuarioEstacion usuarioEstacion = new UsuarioEstacion();
            usuarioEstacion.setIdUsuario(idUsuario);
            usuarioEstacion.setIdHibernadero(idHibernadero);
            usuarioEstacionRepository.save(usuarioEstacion);
            return ResponseEntity.ok(usuarioEstacion);
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
            usuarioEstacionRepository.deleteById(id);
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
            List<UsuarioEstacion> usuarioEstacions = usuarioEstacionRepository.findByIdUsuario(idUsuario);
            return ResponseEntity.ok(usuarioEstacions);
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
            List<UsuarioEstacion> usuarioEstacions = usuarioEstacionRepository.findByIdHibernadero(idHibernadero);
            return ResponseEntity.ok(usuarioEstacions);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
