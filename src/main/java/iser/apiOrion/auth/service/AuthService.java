package iser.apiOrion.auth.service;

import iser.apiOrion.auth.dto.LoginDto;
import iser.apiOrion.collection.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> login(LoginDto loginDto, HttpServletResponse response);

    ResponseEntity<?> register(Usuario usuario, HttpServletRequest request);

}
