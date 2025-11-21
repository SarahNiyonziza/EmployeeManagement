
package org.example.employeemanagement.Service;

import org.example.employeemanagement.Entity.Role;
import org.example.employeemanagement.Entity.User;
import org.example.employeemanagement.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InitialAdminSetupService implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitialAdminSetupService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!userRepository.existsByRole(Role.ROLE_ADMIN)) {
            User admin = User.builder()
                    .fullName("boss Eddy")
                    .username("Eddy")
                    .email("Eddy@gmail.com.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ROLE_ADMIN)
                    .build();

            userRepository.save(admin);

            System.out.println("Username: Eddy");
            System.out.println("Password: admin123");
            System.out.println("Role: ADMIN");

        }


    }
}