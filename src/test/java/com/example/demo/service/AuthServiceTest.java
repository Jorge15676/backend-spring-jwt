// Verifica el servicio de autenticación con una base de datos H2 de pruebas.
package com.example.demo.service;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void register_Success() {
        RegisterRequest request = new RegisterRequest("test@email.com", "123456");

        assertDoesNotThrow(() -> authService.register(request));
        assertTrue(userRepository.findByEmail("test@email.com").isPresent());
    }

    @Test
    void login_Success() {
        RegisterRequest registerReq = new RegisterRequest("user@email.com", "123456");
        authService.register(registerReq);

        LoginRequest loginReq = new LoginRequest("user@email.com", "123456");
        AuthResponse response = authService.login(loginReq);

        assertNotNull(response);
        assertNotNull(response.token());
        assertFalse(response.token().isEmpty());
    }

    @Test
    void login_InvalidPassword_ThrowsException() {
        RegisterRequest registerReq = new RegisterRequest("user@email.com", "123456");
        authService.register(registerReq);

        LoginRequest wrongReq = new LoginRequest("user@email.com", "clave_incorrecta");

        assertThrows(RuntimeException.class, () -> authService.login(wrongReq));
    }
}