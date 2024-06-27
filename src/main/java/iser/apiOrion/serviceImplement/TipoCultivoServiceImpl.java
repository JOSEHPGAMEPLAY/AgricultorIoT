package iser.apiOrion.serviceImplement;

import iser.apiOrion.collection.TipoCultivo;
import iser.apiOrion.repository.TipoCultivoRepository;
import iser.apiOrion.service.TipoCultivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoCultivoServiceImpl implements TipoCultivoService {

    @Autowired
    TipoCultivoRepository tipoCultivoRepository;

    /**
     * Metodo que permite buscar todos los tipos de cultivo
     * @return ResponseEntity con la respuesta de la solicitud
     */
    @Override
    public ResponseEntity<?> buscarTodos() {
        try {
            return ResponseEntity.ok(tipoCultivoRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al buscar los tipos de cultivo");
        }
    }

    /**
     * Metodo que permite buscar un tipo de cultivo por su id
     * @param id identificador del tipo de cultivo
     * @return ResponseEntity con la respuesta de la solicitud
     */
    @Override
    public ResponseEntity<?> buscarPorId(String id) {
        try {
            return ResponseEntity.ok(tipoCultivoRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al buscar el tipo de cultivo");
        }
    }

    /**
     * Metodo que permite guardar un tipo de cultivo
     * @param tipoCultivo objeto con la informacion del tipo de cultivo
     * @return ResponseEntity con la respuesta de la solicitud
     */
    @Override
    public ResponseEntity<?> guardar(TipoCultivo tipoCultivo) {
        try {
            return ResponseEntity.ok(tipoCultivoRepository.save(tipoCultivo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar el tipo de cultivo");
        }
    }

    /**
     * Metodo que permite actualizar un tipo de cultivo
     * @param tipoCultivo objeto con la informacion del tipo de cultivo
     * @return ResponseEntity con la respuesta de la solicitud
     */
    @Override
    public ResponseEntity<?> actualizar(TipoCultivo tipoCultivo) {
        try {
            Optional<TipoCultivo> tipoCultivoOptional = tipoCultivoRepository.findById(tipoCultivo.getId());
            if (tipoCultivoOptional.isEmpty()) {
                return ResponseEntity.badRequest().body("El tipo de cultivo no existe");
            }
            return ResponseEntity.ok(tipoCultivoRepository.save(tipoCultivo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar el tipo de cultivo");
        }
    }

    /**
     * Metodo que permite eliminar un tipo de cultivo
     * @param id identificador del tipo de cultivo
     * @return ResponseEntity con la respuesta de la solicitud
     */
    @Override
    public ResponseEntity<?> eliminar(String id) {
        try {
            Optional<TipoCultivo> tipoCultivoOptional = tipoCultivoRepository.findById(id);
            if (tipoCultivoOptional.isEmpty()) {
                return ResponseEntity.badRequest().body("El tipo de cultivo no existe");
            }
            tipoCultivoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el tipo de cultivo");
        }
    }
}
