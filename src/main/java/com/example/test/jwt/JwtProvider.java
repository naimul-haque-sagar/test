package com.example.test.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSigningSecret;

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .signWith(SignatureAlgorithm.HS512, jwtSigningSecret)
                .compact();
    }

    public boolean validateToken(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSigningSecret)
                    .parseClaimsJws(jwt)
                    .getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromJwt(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(jwtSigningSecret).parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }
}
