package net.bookstores.assignment.homecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.bookstores.assignment.dao.UserDao;
import net.bookstores.assignment.entities.User;

@Controller
public class AccountAuth {
    @Autowired
    UserDao userDao;

    @GetMapping("/xacthuctaikhoan")
    public String xacThucTaiKhoan(@RequestParam("email") String email) {
        User user = userDao.findByEmail(email);
        User userA = User.builder().userId(user.getUserId()).fullName(user.getFullName()).email(user.getEmail())
                .password(user.getPassword()).phone(user.getPhone()).address(user.getAddress())
                .role(false).active(true).build();
        userDao.save(userA);
        return "redirect:/login";
    }
}
