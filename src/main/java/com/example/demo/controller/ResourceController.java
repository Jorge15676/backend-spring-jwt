// Expone un recurso protegido por autenticación JWT.
package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resources")
public class ResourceController {

    @GetMapping
    public ResponseEntity<String> getProtectedResource() {
        return ResponseEntity.ok("Recurso protegido disponible.");
    }
}