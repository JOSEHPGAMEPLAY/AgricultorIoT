package iser.apiOrion.service;

import org.springframework.http.ResponseEntity;

public interface ValidacionService {

    //crear codigo de validacion
    ResponseEntity<?> crearCodigoValidacion(String usuario);

    ResponseEntity<?> validarCodigoValidacion(String usuario, String codigo);

}
