package iser.apiOrion.service;

import org.springframework.http.ResponseEntity;

public interface EstacionService {

    public ResponseEntity<?> buscarTodos();

    public ResponseEntity<?> buscar(String id);

    public ResponseEntity<?> insertar(Object object);

    public ResponseEntity<?> actualizar(Object object);

    public ResponseEntity<?> eliminar(String id);

}
