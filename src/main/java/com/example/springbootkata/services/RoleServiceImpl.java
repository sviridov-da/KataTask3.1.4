package com.example.springbootkata.services;

import com.example.springbootkata.dao.RoleDao;
import com.example.springbootkata.models.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{
    RoleDao dao;
    public RoleServiceImpl(RoleDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return dao.getAllRoles();
    }
}
