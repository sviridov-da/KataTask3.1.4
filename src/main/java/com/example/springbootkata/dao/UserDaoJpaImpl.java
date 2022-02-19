package com.example.springbootkata.dao;

import com.example.springbootkata.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public User getUserById(long id) {
        User res = entityManager.find(User.class, id);
        return res;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(long id, User user) {
        User userToUpdate = entityManager.find(User.class, id);
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
