package com.passport.automation.service;

import com.passport.automation.domain.ApplicationStatus;
import com.passport.automation.domain.PassportApplication;
import com.passport.automation.domain.User;
import com.passport.automation.domain.VerificationStatus;
import com.passport.automation.repository.PassportApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PassportApplicationService {

    @Autowired
    private PassportApplicationRepository applicationRepository;

    public PassportApplication submitApplication(PassportApplication application) {
        application.setStatus(ApplicationStatus.SUBMITTED);
        application.setPoliceVerificationStatus(VerificationStatus.PENDING);
        return applicationRepository.save(application);
    }

    public Optional<PassportApplication> findById(Long id) {
        return applicationRepository.findById(id);
    }

    public List<PassportApplication> getApplicationsByUser(User user) {
        return applicationRepository.findByUser(user);
    }

    public List<PassportApplication> getApplicationsByUserId(Long userId) {
        return applicationRepository.findByUserUserId(userId);
    }

    public List<PassportApplication> getAllApplications() {
        return applicationRepository.findAll();
    }

    public List<PassportApplication> getApplicationsByStatus(ApplicationStatus status) {
        return applicationRepository.findByStatus(status);
    }

    public List<PassportApplication> getApplicationsByVerificationStatus(VerificationStatus status) {
        return applicationRepository.findByPoliceVerificationStatus(status);
    }

    public PassportApplication updateApplicationStatus(Long id, ApplicationStatus status, String remarks) {
        Optional<PassportApplication> optionalApp = applicationRepository.findById(id);
        if (optionalApp.isPresent()) {
            PassportApplication application = optionalApp.get();
            application.setStatus(status);
            application.setRemarks(remarks);
            return applicationRepository.save(application);
        }
        throw new RuntimeException("Application not found");
    }

    public PassportApplication updateVerificationStatus(Long id, VerificationStatus status) {
        Optional<PassportApplication> optionalApp = applicationRepository.findById(id);
        if (optionalApp.isPresent()) {
            PassportApplication application = optionalApp.get();
            application.setPoliceVerificationStatus(status);
            if (status == VerificationStatus.VERIFIED) {
                application.setStatus(ApplicationStatus.UNDER_VERIFICATION);
            }
            return applicationRepository.save(application);
        }
        throw new RuntimeException("Application not found");
    }

    public PassportApplication updateApplication(PassportApplication application) {
        return applicationRepository.save(application);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public long countByStatus(ApplicationStatus status) {
        return applicationRepository.countByStatus(status);
    }
}
