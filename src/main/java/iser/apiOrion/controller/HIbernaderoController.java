package iser.apiOrion.controller;


import iser.apiOrion.collection.Hibernadero;
import iser.apiOrion.service.HibernaderoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hibernadero")
public class HIbernaderoController {

    @Autowired
    HibernaderoService hibernaderoService;

    @GetMapping("/buscarTodos")
    public ResponseEntity<?> buscarTodos() {
        return hibernaderoService.buscarTodos();
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(String id) {
        return hibernaderoService.buscar(id);
    }

    @PostMapping("/insertar")
    public ResponseEntity<?> insertar(Hibernadero object) {
        return hibernaderoService.insertar(object);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(Hibernadero object) {
        return hibernaderoService.actualizar(object);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminar(String id) {
        return hibernaderoService.eliminar(id);
    }

}
