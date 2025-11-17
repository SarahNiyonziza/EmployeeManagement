package org.example.employeemanagement.Repository;

import org.example.employeemanagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
 // extends  jpa that will help connecting to database
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
// will see if the username is already exists
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
