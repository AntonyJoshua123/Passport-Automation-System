package com.passport.automation.repository;

import com.passport.automation.domain.ApplicationStatus;
import com.passport.automation.domain.PassportApplication;
import com.passport.automation.domain.User;
import com.passport.automation.domain.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassportApplicationRepository extends JpaRepository<PassportApplication, Long> {
    
    List<PassportApplication> findByUser(User user);
    
    List<PassportApplication> findByStatus(ApplicationStatus status);
    
    List<PassportApplication> findByPoliceVerificationStatus(VerificationStatus status);
    
    List<PassportApplication> findByUserUserId(Long userId);
    
    long countByStatus(ApplicationStatus status);
}
