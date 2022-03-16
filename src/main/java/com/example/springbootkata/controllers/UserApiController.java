package com.example.springbootkata.controllers;

import com.example.springbootkata.models.UserForm;
import com.example.springbootkata.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/current")
    public UserForm index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new UserForm(userService.getUserByEmail(authentication.getName()));
    }
}
