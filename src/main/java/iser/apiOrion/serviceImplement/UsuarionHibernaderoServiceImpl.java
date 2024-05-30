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


    @Autowired
    UsuarioHibernaderoRepository usuarioHibernaderoRepository;

    @Autowired
    HibernaderoRepository hibernaderoRepository;

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
