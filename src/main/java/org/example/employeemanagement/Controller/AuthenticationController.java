package org.example.employeemanagement.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.employeemanagement.Dto.AuthenticationResponse;
import org.example.employeemanagement.Dto.LoginRequest;
import org.example.employeemanagement.Dto.RegisterRequest;
import org.example.employeemanagement.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }


@Operation(summary = "Register new user")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest req) {
        AuthenticationResponse response = userService.registerEmployee(req);
        return ResponseEntity.ok(response);
    }


@Operation(summary = "User or Admin Login with username and password")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest req) {
        AuthenticationResponse response = userService.login(req);
        return ResponseEntity.ok(response);
    }
}