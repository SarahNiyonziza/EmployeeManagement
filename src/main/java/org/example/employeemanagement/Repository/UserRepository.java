// UserRepository.java
package org.example.employeemanagement.Repository;

import org.example.employeemanagement.Entity.Role;
import org.example.employeemanagement.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByRole(Role role);
    long count();
}