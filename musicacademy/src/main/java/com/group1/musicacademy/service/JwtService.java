package com.group1.musicacademy.service;

import com.group1.musicacademy.model.MyUser;
import com.group1.musicacademy.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final UserRepository repository;

    public JwtService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    private static final String SECRET_KEY = "30416718e66acd5fa664cb0652bedd27898fd8a7a355b51bd4deed1984b14d8b";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }



    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return  generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<String> userRoles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        Optional<MyUser> userOptional = repository.findByUsername(userDetails.getUsername());
        MyUser user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        int userId = user.getId();

        extraClaims.put("roles", userRoles);

        extraClaims.put("userId", userId);


        return Jwts.builder()
                .claims()
                .add(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .and()
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}