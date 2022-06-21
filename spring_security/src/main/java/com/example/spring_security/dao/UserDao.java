package com.example.spring_security.dao;

import com.example.spring_security.model.User;

import java.util.List;

public interface UserDao {

    public List<User> getAllUser();
    public User getUserById (long id);
    public void saveUser (User user);
    public void deleteUser (long id);
    public void editUser (User user);
    public User getUserByUserName (String username);

}
