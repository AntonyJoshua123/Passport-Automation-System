package com.passport.automation.controller;

import com.passport.automation.domain.ApplicationStatus;
import com.passport.automation.domain.PassportApplication;
import com.passport.automation.service.PassportApplicationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/officer")
public class OfficerController {

    @Autowired
    private PassportApplicationService applicationService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        List<PassportApplication> pendingApplications = 
            applicationService.getApplicationsByStatus(ApplicationStatus.UNDER_VERIFICATION);
        
        model.addAttribute("applications", pendingApplications);
        model.addAttribute("totalSubmitted", applicationService.countByStatus(ApplicationStatus.SUBMITTED));
        model.addAttribute("totalUnderVerification", applicationService.countByStatus(ApplicationStatus.UNDER_VERIFICATION));
        model.addAttribute("totalApproved", applicationService.countByStatus(ApplicationStatus.APPROVED));
        model.addAttribute("totalRejected", applicationService.countByStatus(ApplicationStatus.REJECTED));
        
        return "officer/dashboard";
    }

    @GetMapping("/view/{id}")
    public String viewApplication(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        Optional<PassportApplication> application = applicationService.findById(id);
        if (application.isPresent()) {
            model.addAttribute("application", application.get());
            return "officer/view-application";
        }

        return "redirect:/officer/dashboard";
    }

    @PostMapping("/approve/{id}")
    public String approveApplication(@PathVariable Long id,
                                    @RequestParam(required = false) String remarks,
                                    HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        applicationService.updateApplicationStatus(id, ApplicationStatus.APPROVED, remarks);
        return "redirect:/officer/dashboard?approved";
    }

    @PostMapping("/reject/{id}")
    public String rejectApplication(@PathVariable Long id,
                                   @RequestParam String remarks,
                                   HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        applicationService.updateApplicationStatus(id, ApplicationStatus.REJECTED, remarks);
        return "redirect:/officer/dashboard?rejected";
    }

    @GetMapping("/all-applications")
    public String allApplications(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        List<PassportApplication> applications = applicationService.getAllApplications();
        model.addAttribute("applications", applications);
        return "officer/all-applications";
    }
}
