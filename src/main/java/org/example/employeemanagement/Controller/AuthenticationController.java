package org.example.employeemanagement.Controller;

import org.example.employeemanagement.Dto.*;
import org.example.employeemanagement.Security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Authentication", description = "Authentication endpoints for user registration and login")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Operation(summary = "Register a new user", description = "Create a new user account with role-based access")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        MessageResponse response = authenticationService.register(registerRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "User login", description = "Authenticate user and return JWT token")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        TokenResponse response = authenticationService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Admin test endpoint", description = "Test endpoint accessible only to ADMIN role")
    @GetMapping("/admin-test")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminTest() {
        return ResponseEntity.ok(new MessageResponse("Admin endpoint accessed successfully!"));
    }

    @Operation(summary = "User test endpoint", description = "Test endpoint accessible to both USER and ADMIN roles")
    @GetMapping("/user-test")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> userTest() {
        return ResponseEntity.ok(new MessageResponse("User endpoint accessed successfully!"));
    }

    @Operation(summary = "Public test endpoint", description = "Test endpoint accessible without authentication")
    @GetMapping("/public-test")
    public ResponseEntity<?> publicTest() {
        return ResponseEntity.ok(new MessageResponse("Public endpoint accessed successfully!"));
    }
}