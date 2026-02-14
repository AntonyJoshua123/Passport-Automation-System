package com.passport.automation.controller;

import com.passport.automation.domain.User;
import com.passport.automation.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, 
                             BindingResult result, 
                             Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        try {
            userService.registerUser(user);
            return "redirect:/login?registered";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, 
                          HttpSession session, 
                          Model model) {
        Optional<User> authenticatedUser = userService.authenticateUser(
            user.getEmail(), 
            user.getPassword()
        );

        if (authenticatedUser.isPresent()) {
            User loggedInUser = authenticatedUser.get();
            session.setAttribute("userId", loggedInUser.getUserId());
            session.setAttribute("userRole", loggedInUser.getRole());
            session.setAttribute("userName", loggedInUser.getName());

            // Redirect based on role
            switch (loggedInUser.getRole()) {
                case APPLICANT:
                    return "redirect:/applicant/dashboard";
                case PASSPORT_OFFICER:
                    return "redirect:/officer/dashboard";
                case POLICE_OFFICER:
                    return "redirect:/police/dashboard";
                case ADMINISTRATOR:
                    return "redirect:/admin/dashboard";
                default:
                    return "redirect:/";
            }
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
