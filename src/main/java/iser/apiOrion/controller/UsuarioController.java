package iser.apiOrion.controller;

import iser.apiOrion.collection.Usuario;
import iser.apiOrion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/buscarTodos")
    public ResponseEntity<?> buscarTodos() {
        System.out.println("Buscando todos los usuarios...");
        return usuarioService.buscarTodos();
    }

    @PostMapping("/insertar")
    public ResponseEntity<?> insertar(Usuario usuario) {
        return usuarioService.insertar(usuario);
    }

}
