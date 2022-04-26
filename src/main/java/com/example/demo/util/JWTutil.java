package com.example.demo.util;

import com.example.demo.exception.JWTException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTutil {
    @Value("${codingshadows.app.jwtSecret}")
    private String SECRET_KEY;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60*60*24*100)).signWith(key).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) throws JWTException {
        final String username = extractUsername(token);
        if (!username.equals((userDetails.getUsername()))) {
            throw new JWTException(username, "token is not valid.");
        }
        if (isTokenExpired(token)) {
            throw new JWTException(username, "due to token expiration.");
        }
        if(!userDetails.isEnabled()){
            throw new JWTException(username, "the user is disabled.");
        }
        if(!userDetails.isAccountNonExpired()){
            throw new JWTException(username, "the user account is expired.");
        }
        if(!userDetails.isCredentialsNonExpired()){
            throw new JWTException(username, "the credentials are expired.");
        }
        if(!userDetails.isAccountNonLocked()){
            throw new JWTException(username, "the account is locked.");
        }
        return true;
    }
}
