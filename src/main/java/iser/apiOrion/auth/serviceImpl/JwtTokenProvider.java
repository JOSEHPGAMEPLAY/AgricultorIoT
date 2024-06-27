package iser.apiOrion.auth.serviceImpl;


import at.favre.lib.crypto.bcrypt.BCrypt;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import iser.apiOrion.auth.dto.TokenValidationResult;
import iser.apiOrion.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    /**
     * Tiempo de expiracion del token
     */
    @Value("${jwt.expiration.minutes:30}")
    private Integer TOKEN_EXPIRATION_MINUTES;

    /**
     * Clave secreta
     */
    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    /**
     * Lista de URI que no requieren token
     */
    @Value("${jwt.requestURI.startWith-noToken:total-lock}")
    private String listRequestURIStartWithNoToken;

    /**
     * Lista de URI que no requieren token
     */
    @Value("${jwt.requestURI.equals-noToken:total-lock}")
    private String listRequestURIEqualsNoToken;

    /**
     * Repositorio de usuarios
     */
    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * Metodo que permite encriptar una clave
     * @param password clave a encriptar
     * @return clave encriptada
     */
    public static String passwordEncoder(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    /**
     * Metodo que permite comparar una clave encriptada con una clave en texto plano
     * @param rawPassword clave en texto plano
     * @param hashedPassword clave encriptada
     * @return true si la clave en texto plano coincide con la clave encriptada
     */
    public static boolean matchPassword(String rawPassword, String hashedPassword) {
        return BCrypt.verifyer().verify(rawPassword.toCharArray(), hashedPassword).verified;
    }

    /**
     * Metodo que permite obtener la clave secreta
     * @return clave secreta
     */
    public Key getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /**
     * Metodo que permite crear un token
     * @param username nombre de usuario
     * @param idUsuario id del usuario
     * @return token
     */
    public String createToken(String username, String idUsuario) {

        try {
            long timeTokenExpiration;
            timeTokenExpiration = TOKEN_EXPIRATION_MINUTES * 60 * 1000;
            System.out.println("timeTokenExpiration = " + timeTokenExpiration);

            return Jwts.builder()
                    //.setClaims(extraClaims)
                    .setSubject(username)
                    .claim("idUsuario", idUsuario)
                    .signWith(this.getSecretKey())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + timeTokenExpiration))
                    .compact();

        } catch (Exception e) {
            throw new RuntimeException("Error creating token");
        }
    }

    /**
     * Metodo que permite validar un token
     * @param token token a validar
     * @return resultado de la validacion
     */
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
            System.out.println("Error en la validaci\u00f3n del token"+ e.getMessage());
            return new TokenValidationResult(false, null, "Error en la validaci\u00f3n del token");
        }
    }

    /**
     * Metodo que permite obtener el sujeto de un token
     * @param token token
     * @return sujeto
     */
    public String getSubject(String token) {
        return this.resolveToken(token).getClaims().getSubject();
    }

    /**
     * Metodo que permite obtener el cuerpo de un token
     * @param token token
     * @return cuerpo del token
     */
    public String getValueBody(String token) {
        return this.resolveToken(token).getClaims().toString();
    }

    /**
     * Metodo que permite obtener el valor de una clave del cuerpo de un token
     * @param token token
     * @param key clave
     * @return valor de la clave
     */
    public String getValueBody(String token, String key) {
        return this.resolveToken(token).getClaims().get(key).toString();
    }

    /**
     * Metodo que permite extraer un token de una peticion
     * @param req peticion
     * @return token
     */
    public String extractToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        System.out.println("bearerToken = " + bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Metodo que permite validar las URI que no requieren token
     * @param requestURI uri de la peticion
     * @return token
     */
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
