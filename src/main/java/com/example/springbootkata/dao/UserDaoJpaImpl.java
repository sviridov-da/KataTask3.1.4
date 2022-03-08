package com.example.springbootkata.dao;

import com.example.springbootkata.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoJpaImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        List<User> res = query.getResultList();
        return res;
    }

    @Override
    public User getUserById(int id) {
        User res = entityManager.find(User.class, id);
        return res;
    }

    @Override
    public User getUserByEmail(String email) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :login");
        query.setParameter("login", email);
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(int id, User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(int id) {
            entityManager.createQuery("DELETE FROM User u WHERE u.id = :id").setParameter("id", id).executeUpdate();
    }
}
