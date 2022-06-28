package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserDAOHibernateImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<User> getAllUser() {
        List<User> userList = entityManager.createQuery("select u from User u", User.class).getResultList();
        Set<User> userSet = new HashSet<>(userList);
        return userSet;
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public User getUserByUsername(String email) {
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.email = :login", User.class);
            User user = query.setParameter("login", email)
                    .getSingleResult();
            return user;
        } catch (Exception e) {
            return User.NOBODY;
        }
    }
}
