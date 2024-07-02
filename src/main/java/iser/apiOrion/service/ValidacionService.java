package iser.apiOrion.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface ValidacionService {

    ResponseEntity<?> crearCodigoValidacion(String usuario);

    ResponseEntity<?> validarCodigoValidacion(String usuario, String codigo, HttpServletResponse response, HttpServletRequest request);

}
