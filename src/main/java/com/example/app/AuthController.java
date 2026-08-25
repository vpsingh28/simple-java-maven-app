package com.example.app;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    public static class Credentials {
        @NotBlank public String username;
        @NotBlank public String password;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Credentials credentials) {
        try {
            User user = authService.register(credentials.username, credentials.password);
            return ResponseEntity.ok(new AuthResponse("success", user.getId(), user.getUsername(), null));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody Credentials credentials) {
        try {
            return ResponseEntity.ok(new AuthResponse("success", null, credentials.username,
                    authService.login(credentials.username, credentials.password)));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(401).body(new ErrorResponse(ex.getMessage()));
        }
    }

    public record AuthResponse(String status, Long id, String username, String token) {}
    public record ErrorResponse(String error) {}
}
