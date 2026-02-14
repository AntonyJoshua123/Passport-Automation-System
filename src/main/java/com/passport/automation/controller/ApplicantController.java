package com.passport.automation.controller;

import com.passport.automation.domain.PassportApplication;
import com.passport.automation.domain.User;
import com.passport.automation.service.PassportApplicationService;
import com.passport.automation.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {

    @Autowired
    private PassportApplicationService applicationService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        List<PassportApplication> applications = applicationService.getApplicationsByUserId(userId);
        model.addAttribute("applications", applications);
        return "applicant/dashboard";
    }

    @GetMapping("/apply")
    public String showApplicationForm(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        PassportApplication application = new PassportApplication();
        model.addAttribute("application", application);
        return "applicant/apply";
    }

    @PostMapping("/apply")
    public String submitApplication(@ModelAttribute("application") PassportApplication application,
                                   HttpSession session,
                                   Model model) {
        
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        // Debug logging
        System.out.println("=== APPLICATION SUBMISSION DEBUG ===");
        System.out.println("Full Name: " + application.getFullName());
        System.out.println("Date of Birth: " + application.getDateOfBirth());
        System.out.println("Gender: " + application.getGender());
        System.out.println("Address: " + application.getAddress());
        System.out.println("City: " + application.getCity());
        System.out.println("State: " + application.getState());
        System.out.println("Pincode: " + application.getPincode());
        System.out.println("Identity Proof: " + application.getIdentityProof());
        System.out.println("Address Proof: " + application.getAddressProof());
        System.out.println("User ID: " + userId);
        System.out.println("====================================");

        Optional<User> user = userService.findById(userId);

        if (user.isPresent()) {
            application.setUser(user.get());
            
            try {
                PassportApplication savedApp = applicationService.submitApplication(application);
                System.out.println("Application saved with ID: " + savedApp.getApplicationId());
                return "redirect:/applicant/dashboard?success";
            } catch (Exception e) {
                System.err.println("Error saving application: " + e.getMessage());
                e.printStackTrace();
                model.addAttribute("error", "Failed to submit application. Please try again.");
                return "applicant/apply";
            }
        }

        return "redirect:/login";
    }

    @GetMapping("/track/{id}")
    public String trackApplication(@PathVariable Long id, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        Optional<PassportApplication> application = applicationService.findById(id);
        
        // Debug logging
        System.out.println("=== TRACKING APPLICATION ===");
        System.out.println("Application ID: " + id);
        System.out.println("User ID: " + userId);
        System.out.println("Application found: " + application.isPresent());
        if (application.isPresent()) {
            PassportApplication app = application.get();
            System.out.println("Application Full Name: " + app.getFullName());
            System.out.println("Application User ID: " + app.getUser().getUserId());
        }
        System.out.println("============================");
        
        if (application.isPresent() && application.get().getUser().getUserId().equals(userId)) {
            model.addAttribute("application", application.get());
            return "applicant/track";
        }

        return "redirect:/applicant/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        Optional<PassportApplication> application = applicationService.findById(id);
        if (application.isPresent() && application.get().getUser().getUserId().equals(userId)) {
            model.addAttribute("application", application.get());
            return "applicant/edit";
        }

        return "redirect:/applicant/dashboard";
    }

    @PostMapping("/edit/{id}")
    public String updateApplication(@PathVariable Long id,
                                   @ModelAttribute("application") PassportApplication application,
                                   HttpSession session) {
        
        Long userId = (Long) session.getAttribute("userId");
        Optional<PassportApplication> existingApp = applicationService.findById(id);

        if (existingApp.isPresent() && existingApp.get().getUser().getUserId().equals(userId)) {
            application.setApplicationId(id);
            application.setUser(existingApp.get().getUser());
            applicationService.updateApplication(application);
            return "redirect:/applicant/dashboard?updated";
        }

        return "redirect:/applicant/dashboard";
    }
}
