package com.passport.automation.controller;

import com.passport.automation.domain.PassportApplication;
import com.passport.automation.domain.VerificationStatus;
import com.passport.automation.service.PassportApplicationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/police")
public class PoliceController {

    @Autowired
    private PassportApplicationService applicationService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        List<PassportApplication> pendingVerifications = 
            applicationService.getApplicationsByVerificationStatus(VerificationStatus.PENDING);
        
        model.addAttribute("applications", pendingVerifications);
        return "police/dashboard";
    }

    @GetMapping("/verify/{id}")
    public String showVerificationForm(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        Optional<PassportApplication> application = applicationService.findById(id);
        if (application.isPresent()) {
            model.addAttribute("application", application.get());
            return "police/verify";
        }

        return "redirect:/police/dashboard";
    }

    @PostMapping("/verify/{id}")
    public String updateVerification(@PathVariable Long id,
                                    @RequestParam VerificationStatus status,
                                    HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        applicationService.updateVerificationStatus(id, status);
        return "redirect:/police/dashboard?updated";
    }

    @GetMapping("/history")
    public String verificationHistory(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        List<PassportApplication> verified = 
            applicationService.getApplicationsByVerificationStatus(VerificationStatus.VERIFIED);
        List<PassportApplication> notVerified = 
            applicationService.getApplicationsByVerificationStatus(VerificationStatus.NOT_VERIFIED);
        
        model.addAttribute("verified", verified);
        model.addAttribute("notVerified", notVerified);
        return "police/history";
    }
}
