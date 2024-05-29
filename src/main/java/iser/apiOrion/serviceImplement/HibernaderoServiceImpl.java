package iser.apiOrion.serviceImplement;


import iser.apiOrion.collection.Hibernadero;
import iser.apiOrion.repository.HibernaderoRepository;
import iser.apiOrion.service.HibernaderoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HibernaderoServiceImpl implements HibernaderoService {

    @Autowired
    HibernaderoRepository hibernaderoRepository;

    @Override
    public ResponseEntity<?> buscarTodos() {
        try {
            List<Hibernadero> hibernaderos = hibernaderoRepository.findAll();
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
    public ResponseEntity<?> buscar(String id) {
        try {
            Optional<Hibernadero> hibernadero = hibernaderoRepository.findById(id);
            if (hibernadero.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().body(hibernadero);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> insertar(Object object) {
        try {
            Hibernadero hibernadero = (Hibernadero) object;
            hibernaderoRepository.save(hibernadero);
            return ResponseEntity.ok().body(hibernadero);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> actualizar(Object object) {
        try {
            Hibernadero hibernadero = (Hibernadero) object;
            hibernaderoRepository.save(hibernadero);
            return ResponseEntity.ok().body(hibernadero);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> eliminar(String id) {
        try {
            hibernaderoRepository.deleteById(id);
            return ResponseEntity.ok().body("Hibernadero eliminado");
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
