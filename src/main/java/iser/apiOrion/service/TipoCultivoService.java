package iser.apiOrion.service;

import iser.apiOrion.collection.TipoCultivo;
import org.springframework.http.ResponseEntity;

public interface TipoCultivoService {

    ResponseEntity<?> buscarTodos();

    ResponseEntity<?> buscarPorId(String id);

    ResponseEntity<?> guardar(TipoCultivo tipoCultivo);

    ResponseEntity<?> actualizar(TipoCultivo tipoCultivo);

    ResponseEntity<?> eliminar(String id);

}
