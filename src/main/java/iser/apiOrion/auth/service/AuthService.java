package iser.apiOrion.auth.service;

import iser.apiOrion.auth.dto.LoginDto;
import iser.apiOrion.collection.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> login(LoginDto loginDto);

    ResponseEntity<?> register(Usuario usuario, HttpServletRequest request);

}
