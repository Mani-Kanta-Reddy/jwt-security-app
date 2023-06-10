package com.neon.jwtsecurityapp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService
{
    private static final String SECRET = "2646294A404E635266546A576E5A7234753778214125442A472D4B6150645367";
    public String generateToken(UserDetails user)
    {
        return generateToken(Map.of(), user);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenValid(String token, UserDetails user) {
        return !isTokenExpired(token) && extractUsername(token).equals(user.getUsername());
    }

    private boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(Date.from(Instant.now()));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String generateToken(Map<String, Object> claims, UserDetails user) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.getUsername())
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plusSeconds(60 * 60)))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();

    }

    private Key getSignInKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

}
