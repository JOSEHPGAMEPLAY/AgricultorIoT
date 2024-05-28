package iser.apiOrion.service;

import org.springframework.http.ResponseEntity;

public interface HibernaderoService {

    public ResponseEntity<?> buscarTodos();

    public ResponseEntity<?> buscar(String id);

    //crud
    public ResponseEntity<?> insertar(Object object);

    public ResponseEntity<?> actualizar(Object object);

    public ResponseEntity<?> eliminar(String id);

}
