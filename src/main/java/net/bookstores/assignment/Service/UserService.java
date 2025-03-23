package net.bookstores.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bookstores.assignment.dao.UserDao;
import net.bookstores.assignment.entities.User;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public List<User> findAll() {
        List<User> usersList = userDao.findAll();
        return usersList;
    }
}
