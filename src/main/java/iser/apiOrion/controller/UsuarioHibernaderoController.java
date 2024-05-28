package iser.apiOrion.controller;


import iser.apiOrion.service.UsuarioHibernaderoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarioHibernadero")
public class UsuarioHibernaderoController {

    @Autowired
    UsuarioHibernaderoService usuarioHibernaderoService;

    //consultar todos los hibernaderos de un usuario
    @GetMapping("/buscarHibernaderosUsuario")
    public ResponseEntity<?> buscarTodos(String id) {
        return usuarioHibernaderoService.buscarHibernaderosUsuario(id);
    }

}
