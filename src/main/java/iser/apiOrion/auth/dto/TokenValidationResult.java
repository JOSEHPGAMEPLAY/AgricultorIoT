package iser.apiOrion.auth.dto;

import io.jsonwebtoken.Claims;
import lombok.Data;

@Data
public class TokenValidationResult {

    private boolean valid;
    private Claims claims;
    private String message;

    public TokenValidationResult(boolean valid, Claims claims, String message) {
        this.valid = valid;
        this.claims = claims;
        this.message = message;
    }
}
