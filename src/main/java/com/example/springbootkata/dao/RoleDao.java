package com.example.springbootkata.dao;

import com.example.springbootkata.models.Role;
import com.example.springbootkata.models.User;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRoles();
    Role getRoleById(int id);
    void addRole(Role role);
    void update(int id, Role role);
    void deleteRole(int id);
}
