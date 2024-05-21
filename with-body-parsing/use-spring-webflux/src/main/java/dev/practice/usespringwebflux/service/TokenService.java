package dev.practice.usespringwebflux.service;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class TokenService {

    public String extractToken(String body) {

        // application/x-www-form-urlencoded 형식에서 JWT 토큰을 추출

        String[] pairs = body.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2 && "token".equals(keyValue[0])) {
                return new String(Base64.getDecoder().decode(keyValue[1]));
            }
        }
        throw new RuntimeException("JWT token not found in request body"); // todo, 4xx
    }

    public String getIssuerFromToken(String token) {
        try {
            JWT jwt = JWTParser.parse(token);
            return jwt.getJWTClaimsSet().getIssuer();
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e); // todo, 4xx
        }
    }
}
