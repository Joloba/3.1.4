package com.example.spring_security.configs;

import com.example.spring_security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import com.example.spring_security.model.Role;
import com.example.spring_security.model.User;
import com.example.spring_security.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public DataLoader(UserService userService, RoleService roleService, PasswordEncoder encoder) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (roleService.getRole("ROLE_ADMIN") == null) {
            roleService.addRole(new Role("ROLE_ADMIN"));
        }

        if (roleService.getRole("USER") == null) {
            roleService.addRole(new Role("ROLE_USER"));
        }

        if (userService.getUserByUserName("admin") == null) {
            User sergey = new User("admin", "admin", "Sergey", "Polunin", (byte)27, "admin@mail.ru");
            sergey.setRolesList(roleService.getRole("ROLE_ADMIN"));
            sergey.setRolesList(roleService.getRole("ROLE_USER"));
            userService.saveUser(sergey);
        }

        if (userService.getUserByUserName("user") == null) {
            User ivan = new User("user", "user", "Ivan","Petrov", (byte)43, "user@mail.ru");
            ivan.setRolesList(roleService.getRole("ROLE_USER"));
            userService.saveUser(ivan);
        }

    }
}
