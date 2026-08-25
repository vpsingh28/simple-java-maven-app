package com.example.app.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import com.example.app.AuthController.AuthResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthIntegrationTest {
    @Autowired TestRestTemplate rest;

    @Test
    void registerAndLoginReturnSuccessJson() {
        String username = "user-" + System.nanoTime();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = "{\"username\":\"" + username + "\",\"password\":\"secret\"}";
        ResponseEntity<AuthResponse> registered = rest.exchange("/api/v1/auth/register", HttpMethod.POST,
                new HttpEntity<>(body, headers), AuthResponse.class);
        assertEquals(HttpStatus.OK, registered.getStatusCode());
        assertEquals("success", registered.getBody().status());
        assertEquals(username, registered.getBody().username());
        ResponseEntity<AuthResponse> loggedIn = rest.exchange("/api/v1/auth/login", HttpMethod.POST,
                new HttpEntity<>(body, headers), AuthResponse.class);
        assertEquals(HttpStatus.OK, loggedIn.getStatusCode());
        assertEquals("success", loggedIn.getBody().status());
        assertNotNull(loggedIn.getBody().token());
    }
}
