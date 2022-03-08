package com.example.springbootkata.dao;

import com.example.springbootkata.models.Role;
import com.example.springbootkata.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Repository
public class RoleDaoJpaImpl implements RoleDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        Query query = entityManager.createQuery("SELECT r FROM Role r");
        List<Role> res = query.getResultList();
        return res;
    }

    @Override
    public Role getRoleById(int id) {
        Role res = entityManager.find(Role.class, id);
        return res;
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void update(int id, Role role) {
        entityManager.merge(role);
    }

    @Override
    public void deleteRole(int id) {
        entityManager.createQuery("DELETE FROM Role r WHERE r.id = :id").setParameter("id", id).executeUpdate();
    }
}
