package org.example.worktrack.controller;

import org.example.worktrack.DTOs.UserDTO;
import org.example.worktrack.DTOs.UserRegistrationDTO;
import org.example.worktrack.entities.User;
import org.example.worktrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signupPage(Model model){
        model.addAttribute("newUser", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String register(@ModelAttribute UserDTO userDTO) {
        userService.register(new UserRegistrationDTO());
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

}
