package iser.apiOrion.controller;


import iser.apiOrion.service.UsuarioHibernaderoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarioHibernadero")
public class UsuarioHibernaderoController {

    @Autowired
    UsuarioHibernaderoService usuarioHibernaderoService;

    @GetMapping("/buscarHibernaderosUsuario")
    public ResponseEntity<?> buscarTodos(String id) {
        return usuarioHibernaderoService.buscarHibernaderosUsuario(id);
    }

    @GetMapping("/buscarPorId")
    public ResponseEntity<?> buscarPorId(String id) {
        return usuarioHibernaderoService.buscarPorId(id);
    }

    @PostMapping("/crearUsuarioHibernadero")
    public ResponseEntity<?> crearUsuarioHibernadero(String idUsuario, String idHibernadero) {
        return usuarioHibernaderoService.crearUsuarioHibernadero(idUsuario, idHibernadero);
    }

    @DeleteMapping("/borrarUsuarioHibernadero")
    public ResponseEntity<?> borrarUsuarioHibernadero(String id) {
        return usuarioHibernaderoService.borrarUsuarioHibernadero(id);
    }

    @GetMapping("/buscarPorUsuario")
    public ResponseEntity<?> buscarPorUsuario(String idUsuario) {
        return usuarioHibernaderoService.buscarPorUsuario(idUsuario);
    }

    @GetMapping("/buscarPorHibernadero")
    public ResponseEntity<?> buscarPorHibernadero(String idHibernadero) {
        return usuarioHibernaderoService.buscarPorHibernadero(idHibernadero);
    }

}
