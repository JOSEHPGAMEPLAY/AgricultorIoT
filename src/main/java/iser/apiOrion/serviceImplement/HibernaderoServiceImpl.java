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

    /**
     * Repositorio de hibernaderos
     */
    @Autowired
    HibernaderoRepository hibernaderoRepository;

    /**
     * Metodo que permite obtener todos los hibernaderos
     * @return lista de hibernaderos
     */
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

    /**
     * Metodo que permite obtener un hibernadero por su id
     * @param id id del hibernadero
     * @return hibernadero
     */
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

    /**
     * Metodo que permite insertar un hibernadero
     * @param object hibernadero
     * @return hibernadero insertado
     */
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

    /**
     * Metodo que permite actualizar un hibernadero
     * @param object hibernadero
     * @return hibernadero actualizado
     */
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

    /**
     * Metodo que permite eliminar un hibernadero
     * @param id id del hibernadero
     * @return mensaje de confirmacion
     */
    @Override
    public ResponseEntity<?> eliminar(String id) {
        try {
            System.out.println("id: "+id);
            Optional<Hibernadero> hibernadero = hibernaderoRepository.findById(id);
            if (hibernadero.isEmpty()) {
                System.out.println("No se encontro el hibernadero");
                return ResponseEntity.noContent().build();
            }
            System.out.println("hibernadero: "+hibernadero.get());
            hibernaderoRepository.deleteById(hibernadero.get().getId());
            return ResponseEntity.ok().body("Hibernadero eliminado");
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
