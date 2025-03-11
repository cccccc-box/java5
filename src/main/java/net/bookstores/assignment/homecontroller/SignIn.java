package net.bookstores.assignment.homecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.bookstores.assignment.dao.UserDao;
import net.bookstores.assignment.entities.User;
import net.bookstores.assignment.util.MailService;

@Controller
public class SignIn {
    @Autowired
    UserDao userDao;

    @Autowired
    MailService mailService;

    @GetMapping("/signin")
    public String signIn() {
        return "home/register";
    }

    @ResponseBody
    @PostMapping("/signin/create")
    public String create(@Param("fullName") String fullName, @Param("email") String email,
            @Param("password") String password, @Param("phone") String phone, @Param("address") String address) {
        try {
            User user = User.builder().fullName(fullName).email(email).password(password).phone(phone).address(address)
                    .active(false).build();
            userDao.save(user);

            String a = "<a href=\"http://localhost:8080/xacthuctaikhoan?email=" + user.getEmail()
                    + "\">Nhấn vào đây để xác thực</a>";
            String toEmail = user.getEmail();
            String emailBody = "Chúc mừng bạn đã đăng ký tài khoản thành công! " + a;
            mailService.send(toEmail, "Xác thực tài khoản Vbooks.net", emailBody);

        } catch (Exception e) {
            return "<script>alert('Đăng ký thất bại: " + e.getMessage() + "');"
                    + "window.history.back();</script>";
        }
        return "<script>alert('Đăng ký thành công! Vui lòng kiểm tra email để xác thực tài khoản.');"
                + "window.location.href='/login';</script>";
    }
}
