package com.example.springbootkata.services;

import com.example.springbootkata.dao.UserDao;
import com.example.springbootkata.models.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    private UserDao dao;
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return dao.getUserById(id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(long id, User user) {
        dao.update(id, user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        dao.deleteUser(id);
    }
}
