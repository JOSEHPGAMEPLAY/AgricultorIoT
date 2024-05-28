package iser.apiOrion.auth.serviceImpl;


import at.favre.lib.crypto.bcrypt.BCrypt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import iser.apiOrion.auth.dto.LoginDto;
import iser.apiOrion.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private Integer TOKEN_EXPIRATION_MINUTES = 30;// contiene la duracion de la session en minutos

    private String SECRET_KEY = "secretkey98wef54v321sdf324bn65x4dv32d4fb365sdv564fb23bnf43cvfd654xc32b4d6f5b4xs32gn464n3h5n4dcbdfbn4c65d4b53486s453x1bc86f4b32c54bxf4b";

    @Autowired
    UsuarioRepository usuarioRepository;


    public static String passwordEncoder(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
    public static boolean matchPassword(String rawPassword, String hashedPassword) {
        return BCrypt.verifyer().verify(rawPassword.toCharArray(), hashedPassword).verified;
    }

    public String createToken(LoginDto loginDto) {
        System.out.println(
                "loginDto.getUsuario() = " + loginDto.getUsuario() +
                        "loginDto.getClave() = " + loginDto.getClave()
        );

        try {
            long timeTokenExpiration;
            timeTokenExpiration = TOKEN_EXPIRATION_MINUTES * 60 * 1000;
            System.out.println("timeTokenExpiration = " + timeTokenExpiration);

            String token = Jwts.builder()
                    .setSubject(loginDto.getUsuario())
                    .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + timeTokenExpiration))
                    .compact();

            System.out.println("token creado" + token);

            return Jwts.builder()
                    //.setClaims(extraClaims)
                    .setSubject(loginDto.getUsuario())
                    .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + timeTokenExpiration))
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getSubject(String token) {
        return Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String getValueBody(String token, String key) {
        return Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).parseClaimsJws(token).getBody().get(key).toString();
    }


}
