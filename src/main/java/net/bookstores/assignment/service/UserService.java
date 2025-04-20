package net.bookstores.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import net.bookstores.assignment.dao.UserDao;
import net.bookstores.assignment.entities.User;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    HttpServletRequest request;
    
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userDao.findById(id);
    }

    @SuppressWarnings("null")
    public Optional<User> readCookie() {
        Cookie[] cookies = request.getCookies();
        Integer userId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())) { // Tìm cookie có tên "user"
                    userId = Integer.parseInt(cookie.getValue()); // Lấy giá trị cookie (User ID)
                }
            }
        }
        return userDao.findById(userId);
    }

    public User createUser(String fullName, String email, String password, String phone, String address, Boolean role,
            Boolean active) {
        User user = User.builder()
                .fullName(fullName)
                .email(email)
                .password(password)
                .phone(phone)
                .address(address)
                .role(role)
                .active(active)
                .build();
        return userDao.save(user);
    }

    public User updateUser(Integer userId, String fullName, String email, String phone, String address, Boolean role,
            Boolean active) {
        Optional<User> existingUser = userDao.findById(userId);
        if (existingUser.isPresent()) {
            User user = User.builder()
                    .userId(userId)
                    .fullName(fullName)
                    .password(existingUser.get().getPassword()) // Giữ nguyên mật khẩu
                    .email(email)
                    .phone(phone)
                    .address(address)
                    .role(role)
                    .active(active)
                    .build();
            return userDao.save(user);
        }
        return null;
    }

    public User updateUser(Integer userId, String fullName, String email, String phone, String address) {
        Optional<User> existingUser = userDao.findById(userId);
        if (existingUser.isPresent()) {
            User user = User.builder()
                    .userId(userId)
                    .fullName(fullName)
                    .password(existingUser.get().getPassword()) // Giữ nguyên mật khẩu
                    .email(email)
                    .phone(phone)
                    .address(address)
                    .build();
            return userDao.save(user);
        }
        return null;
    }

    public void deleteUser(Integer id) {
        userDao.deleteById(id);
    }

}
