package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

@Transactional
@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Set<User> getAllUser() {
        return userDAO.getAllUser();
    }

    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    public void editUser(User user) {
        userDAO.editUser(user);
    }

    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }

    public User getUserByUsername(String email) {
        return userDAO.getUserByUsername(email);
    }

}
