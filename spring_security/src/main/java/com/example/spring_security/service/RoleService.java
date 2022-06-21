package com.example.spring_security.service;

import com.example.spring_security.dao.RoleDao;
import com.example.spring_security.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Role getRole(String userRole) {
        return roleDao.getRole(userRole);
    }

    public List<Role> getAllRole() {
        return roleDao.getAllRole();
    }

    public Role getRole(long roleId) {
        return roleDao.getRole(roleId);
    }

    public void addRole(Role role) {
        roleDao.addRole(role);
    }
}