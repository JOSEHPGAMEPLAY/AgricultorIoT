package iser.apiOrion.service;

import iser.apiOrion.collection.Formulario;
import org.springframework.http.ResponseEntity;

public interface FormularioService {

    ResponseEntity<?> buscarTodos();

    ResponseEntity<?> buscarPorId(String id);

    ResponseEntity<?> actualizar(Formulario formulario);

    ResponseEntity<?> borrar(String id);

    ResponseEntity<?> crear(Formulario formulario);

}
