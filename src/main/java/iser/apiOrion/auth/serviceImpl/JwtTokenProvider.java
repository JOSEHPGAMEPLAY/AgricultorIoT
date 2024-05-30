package iser.apiOrion.auth.serviceImpl;


import at.favre.lib.crypto.bcrypt.BCrypt;
import io.jsonwebtoken.*;

import java.security.Key;

import io.jsonwebtoken.security.Keys;
import iser.apiOrion.auth.dto.LoginDto;
import iser.apiOrion.auth.dto.TokenValidationResult;
import iser.apiOrion.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.expiration.minutes:30}")
    private Integer TOKEN_EXPIRATION_MINUTES;

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${jwt.requestURI.startWith-noToken:total-lock}")
    private String listRequestURIStartWithNoToken;

    @Value("${jwt.requestURI.equals-noToken:total-lock}")
    private String listRequestURIEqualsNoToken;

    @Autowired
    UsuarioRepository usuarioRepository;


    public static String passwordEncoder(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean matchPassword(String rawPassword, String hashedPassword) {
        return BCrypt.verifyer().verify(rawPassword.toCharArray(), hashedPassword).verified;
    }

    public Key getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String createToken(String username) {

        try {
            long timeTokenExpiration;
            timeTokenExpiration = TOKEN_EXPIRATION_MINUTES * 60 * 1000;
            System.out.println("timeTokenExpiration = " + timeTokenExpiration);

            String token = Jwts.builder()
                    //.setClaims(extraClaims)
                    .setSubject(username)
                    .signWith(this.getSecretKey())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + timeTokenExpiration))
                    .compact();

            System.out.println("token creado" + token);

            return token;

        } catch (Exception e) {
            throw new RuntimeException("Error creating token");
        }
    }

    public TokenValidationResult resolveToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(this.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
            return new TokenValidationResult(true, claims, "Token v\u00e1lido");
        } catch (ExpiredJwtException e) {
            return new TokenValidationResult(false, e.getClaims(), "Token expirado");
        } catch (UnsupportedJwtException e) {
            return new TokenValidationResult(false, null, "Token no soportado");
        } catch (MalformedJwtException e) {
            return new TokenValidationResult(false, null, "Token mal formado");
        } catch (SignatureException e) {
            return new TokenValidationResult(false, null, "Firma no v\u00e1lida");
        } catch (Exception e) {
            return new TokenValidationResult(false, null, "Error en la validaci\u00f3n del token");
        }
    }

    public String getSubject(String token) {
        return this.resolveToken(token).getClaims().getSubject();
    }

    public String getValueBody(String token) {
        return this.resolveToken(token).getClaims().toString();
    }

    public String getValueBody(String token, String key) {
        return this.resolveToken(token).getClaims().get(key).toString();
    }

    public String extractToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean requestURINoToken(String requestURI) {
        if (listRequestURIStartWithNoToken != null) {
            if (listRequestURIStartWithNoToken.equals("total-lock")) {
                return false;
            }
            String[] listRequestURIStartWith = listRequestURIStartWithNoToken.split(";");
            for (String value : listRequestURIStartWith) {
                if (requestURI.startsWith(value)) {
                    return true;
                }
            }
        }
        if (listRequestURIEqualsNoToken != null) {
            if (listRequestURIEqualsNoToken.equals("total-lock")) {
                return false;
            }
            String[] listRequestURIEquals = listRequestURIEqualsNoToken.split(";");
            for (String value : listRequestURIEquals) {
                if (requestURI.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }


}
