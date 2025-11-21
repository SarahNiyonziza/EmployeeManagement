package org.example.employeemanagement.Service;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.example.employeemanagement.Dto.AuthenticationResponse;
import org.example.employeemanagement.Dto.LoginRequest;
import org.example.employeemanagement.Dto.RegisterRequest;
import org.example.employeemanagement.Entity.Role;
import org.example.employeemanagement.Entity.User;
import org.example.employeemanagement.Repository.UserRepository;
import org.example.employeemanagement.Security.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import static jdk.internal.classfile.impl.DirectCodeBuilder.build;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtils jwtUtils,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }


    public AuthenticationResponse login(LoginRequest req) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        User user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        String token = jwtUtils.generateToken(user.getUsername(), user.getRole().name());

        return AuthenticationResponse.builder()
                .message("Login successful")
                .token(token)

                .build();
    }





    @Transactional
    public AuthenticationResponse registerEmployee(@Valid RegisterRequest req) {
        return registerUser(req, Role.ROLE_ADMIN);
    }




    private AuthenticationResponse registerUser(RegisterRequest req, Role role) {
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = User.builder()
                .username(req.getUsername())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(Role.valueOf(req.getRole()))
                .build();

        User saved = userRepository.save(user);



        return AuthenticationResponse.builder()
                .message("User registered successfully")
              // .role(saved.getRole().name())
                .build();
    }
}