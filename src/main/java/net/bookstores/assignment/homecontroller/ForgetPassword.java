package net.bookstores.assignment.homecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.bookstores.assignment.dao.UserDao;
import net.bookstores.assignment.entities.User;
import net.bookstores.assignment.util.MailService;

@Controller
public class ForgetPassword {

    @Autowired
    MailService mailService;

    @Autowired
    UserDao userDao;

    @GetMapping("/forgetpassword")
    public String forgetPassword() {
        return "home/forgetpassword";
    }

    @PostMapping("/forgetpassword")
    public Object sendPassword(@RequestParam("email") String email, Model model) {
        User user = userDao.findByEmail(email);
        if (user != null) {
            String password = user.getPassword();
            String u = "<b>" + password + "</b>";
            String emailBody = "Mật khẩu cũ của bạn là: " + u + "<br>"
                    + "<a href=\"http://localhost:8080/login\">Đăng nhập tại đây</a>";
            mailService.send(email, "Mật khẩu tài khoản Vbooks.net của bạn", emailBody);

            return ResponseEntity.ok("<script>alert('Đã gửi lại mật khẩu cũ cho bạn!');"
                    + "window.location.href='/login';</script>");
        } else {
            model.addAttribute("message", "Email không tồn tại, Vui lòng nhập lại!");
            return "home/forgetpassword";
        }
    }
}
