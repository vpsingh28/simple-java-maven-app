package com.example.app;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final Key signingKey;
    private final long expiryMillis;

    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder,
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiry-minutes:30}") long expiryMinutes) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes.length >= 32 ? keyBytes : (secret + "012345678901234567890123456789").substring(0, 32).getBytes(StandardCharsets.UTF_8));
        this.expiryMillis = expiryMinutes * 60_000L;
    }

    public User register(String username, String password) {
        if (repository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        return repository.save(new User(username, passwordEncoder.encode(password)));
    }

    public String login(String username, String password) {
        User user = repository.findByUsername(username)
                .filter(candidate -> passwordEncoder.matches(password, candidate.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        Date now = new Date();
        return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiryMillis))
                .signWith(signingKey, SignatureAlgorithm.HS256).compact();
    }
}
