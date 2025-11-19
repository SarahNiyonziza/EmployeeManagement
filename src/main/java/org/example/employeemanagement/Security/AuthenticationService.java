package org.example.employeemanagement.Security;

import org.example.employeemanagement.Dto.*;

public interface AuthenticationService {
    MessageResponse register(RegisterRequest registerRequest);
    TokenResponse login(LoginRequest loginRequest); // Changed return type
}