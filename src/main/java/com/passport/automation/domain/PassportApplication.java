package com.passport.automation.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "passport_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Address is required")
    @Column(length = 500)
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Pincode is required")
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
    private String pincode;

    @NotBlank(message = "Identity proof is required")
    private String identityProof;

    @NotBlank(message = "Address proof is required")
    private String addressProof;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDate appointmentDate;
    
    private String appointmentTime;

    @Enumerated(EnumType.STRING)
    private VerificationStatus policeVerificationStatus;

    private String remarks;

    @Column(updatable = false)
    private LocalDateTime submittedDate;

    private LocalDateTime lastUpdatedDate;

    @PrePersist
    protected void onCreate() {
        submittedDate = LocalDateTime.now();
        lastUpdatedDate = LocalDateTime.now();
        if (status == null) {
            status = ApplicationStatus.SUBMITTED;
        }
        if (policeVerificationStatus == null) {
            policeVerificationStatus = VerificationStatus.PENDING;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdatedDate = LocalDateTime.now();
    }
}
