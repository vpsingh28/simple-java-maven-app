package com.example.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import com.example.app.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock UserRepository repository;
    @Mock PasswordEncoder passwordEncoder;
    AuthService service;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        service = new AuthService(repository, passwordEncoder,
                "test-secret-key-that-is-at-least-32", 30);
    }

    @Test
    void registerStoresEncodedPassword() {
        when(repository.findByUsername("alice")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("secret")).thenReturn("encoded");
        when(repository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        User user = service.register("alice", "secret");
        assertEquals("alice", user.getUsername());
        assertEquals("encoded", user.getPassword());
        verify(repository).save(any(User.class));
    }

    @Test
    void duplicateUsernameIsRejected() {
        when(repository.findByUsername("alice")).thenReturn(Optional.of(new User("alice", "encoded")));
        assertThrows(IllegalArgumentException.class, () -> service.register("alice", "secret"));
        verify(repository, never()).save(any());
    }
}
