package com.passport.automation.controller;

import com.passport.automation.domain.User;
import com.passport.automation.domain.UserRole;
import com.passport.automation.service.PassportApplicationService;
import com.passport.automation.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PassportApplicationService applicationService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        model.addAttribute("totalUsers", allUsers.size());
        model.addAttribute("totalApplications", applicationService.getAllApplications().size());
        
        return "admin/dashboard";
    }

    @GetMapping("/users")
    public String manageUsers(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/users/role/{role}")
    public String getUsersByRole(@PathVariable UserRole role, HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        List<User> users = userService.getUsersByRole(role);
        model.addAttribute("users", users);
        model.addAttribute("role", role);
        return "admin/users-by-role";
    }

    @PostMapping("/users/toggle-status/{id}")
    public String toggleUserStatus(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        User user = userService.findById(id).orElseThrow();
        user.setActive(!user.isActive());
        userService.updateUser(user);
        
        return "redirect:/admin/users?updated";
    }

    @GetMapping("/reports")
    public String generateReports(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        model.addAttribute("applications", applicationService.getAllApplications());
        return "admin/reports";
    }
}
