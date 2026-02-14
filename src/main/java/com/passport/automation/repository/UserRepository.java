package com.passport.automation.repository;

import com.passport.automation.domain.User;
import com.passport.automation.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByEmailAndPassword(String email, String password);
    
    List<User> findByRole(UserRole role);
    
    boolean existsByEmail(String email);
    
    List<User> findByActiveTrue();
}
