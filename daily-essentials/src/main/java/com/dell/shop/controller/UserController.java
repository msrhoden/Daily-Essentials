package com.dell.shop.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dell.shop.user.User;
import com.dell.shop.user.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/report")
    public String userReportPanel(Principal principal, Model model){
        List<User> user = userService.findAll();

        if (user != null) {
            model.addAttribute("users", user);
        }else {
            return "error/404";
        }

        return "report";
    }

    @GetMapping("/user")
    public String userPanel(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());

        if (user != null) {
            model.addAttribute("user", user);
        }else {
            return "error/404";
        }

        return "user";
    }
}
