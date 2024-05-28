package iser.apiOrion.service;

import org.springframework.http.ResponseEntity;

public interface UsuarioHibernaderoService {

    public ResponseEntity<?> buscarHibernaderosUsuario(String id);

}
