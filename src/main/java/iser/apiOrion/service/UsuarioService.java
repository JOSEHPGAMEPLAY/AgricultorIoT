package iser.apiOrion.service;

import iser.apiOrion.collection.Usuario;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {

    ResponseEntity<?> insertar(Usuario usuario);

    ResponseEntity<?> actualizar(Usuario usuario);

    ResponseEntity<?> eliminar(String id);

    ResponseEntity<?> buscar(String id);

    ResponseEntity<?> buscarTodos();

}
