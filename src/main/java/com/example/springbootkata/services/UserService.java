package com.example.springbootkata.services;

import com.example.springbootkata.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserById(int id);
    void addUser(User user);
    void updateUser(int id, User user);
    void deleteUser(int id);
    User getUserByEmail(String email);
}
