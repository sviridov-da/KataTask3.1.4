package com.example.springbootkata.controllers;

import com.example.springbootkata.models.Role;
import com.example.springbootkata.models.User;
import com.example.springbootkata.models.UserForm;
import com.example.springbootkata.services.RoleService;
import com.example.springbootkata.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {
    private UserService userService;
    private RoleService roleService;

    public AdminApiController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/users")
    public List<UserForm> getAllUsers() {
        return userService.getAllUsers().stream().map(UserForm::new).collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public UserForm getUserById(@PathVariable("id") int id){
        return new UserForm(userService.getUserById(id));
    }

    @PostMapping("/users")
    public UserForm addUser(@ModelAttribute("user") UserForm user){
        userService.addUser(new User(user, roleService));
        return new UserForm(userService.getUserByEmail(user.getEmail()));
    }

    @PatchMapping("/users")
    public UserForm updateUser(@ModelAttribute("user") UserForm user) {
        User editingUser = userService.getUserById(user.getId());
        editingUser.update(user, roleService);
        userService.updateUser(user.getId(), editingUser);
        return new UserForm(userService.getUserById(user.getId()));
    }

    @DeleteMapping("users/{id}")
    public UserForm deleteUser(@PathVariable("id") int id) {
        UserForm res = new UserForm(userService.getUserById(id));
        userService.deleteUser(id);
        return res;
    }
}
