package com.example.spring_security.service;

import com.example.spring_security.dao.UserDao;
import com.example.spring_security.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    public List<User> getAllUser();

    public User getUserById (long id);

    public void saveUser (User user);

    public void deleteUser (long id);

    public void editUser (User user);

    public User getUserByUserName (String username);

}
