package org.example.employeemanagement.Security;

import org.example.employeemanagement.Dto.JwtResponse;
import org.example.employeemanagement.Dto.LoginRequest;
import org.example.employeemanagement.Dto.MessageResponse;
import org.example.employeemanagement.Dto.RegisterRequest;

public interface AuthenticationService {
    MessageResponse register(RegisterRequest registerRequest);
    JwtResponse login(LoginRequest loginRequest);
}