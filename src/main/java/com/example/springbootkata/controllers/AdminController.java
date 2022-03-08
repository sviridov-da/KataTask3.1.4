package com.example.springbootkata.controllers;

import com.example.springbootkata.models.User;
import com.example.springbootkata.models.UserForm;
import com.example.springbootkata.services.RoleService;
import com.example.springbootkata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;

@Controller
@RequestMapping("/admin/users")
public class AdminController {

    private UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", userService.getUserByEmail(authentication.getName()));
        return "admin/users_table";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        System.out.println(userService.getUserById(id).getName());
        return "admin/user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") UserForm user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/new_user";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") UserForm user) {
        userService.addUser(new User(user, roleService));
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String createUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", new UserForm(userService.getUserById(id)));
        return "admin/edit_user";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") UserForm user, @PathVariable("id") int id) {
        User editingUser = userService.getUserById(id);
        editingUser.update(user, roleService);
        userService.updateUser(id, editingUser);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
