package org.example.employeemanagement.Controller;

import org.example.employeemanagement.Dto.*;
import org.example.employeemanagement.Security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        MessageResponse response = authenticationService.register(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse response = authenticationService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    // ADD THESE NEW ENDPOINTS FOR ROLE TESTING:

    @GetMapping("/admin-test")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminTest() {
        return ResponseEntity.ok(new MessageResponse("Admin !"));
    }

    @GetMapping("/user-test")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> userTest() {
        return ResponseEntity.ok(new MessageResponse("User !"));
    }

    @GetMapping("/public-test")
    public ResponseEntity<?> publicTest() {
        return ResponseEntity.ok(new MessageResponse("Public endpoint accessed successfully!"));
    }
}