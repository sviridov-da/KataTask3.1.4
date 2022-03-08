package com.example.springbootkata.dao;

import com.example.springbootkata.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByEmail(String email);
    void addUser(User user);
    void update(int id, User user);
    void deleteUser(int id);
}
