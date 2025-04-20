package net.bookstores.assignment.homecontroller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import net.bookstores.assignment.dao.UserDao;
import net.bookstores.assignment.entities.User;
import net.bookstores.assignment.service.AuthService;

@Controller
public class ChangePassword {

    @Autowired
    HttpSession session;

    @Autowired
    UserDao userDao;

    @Autowired
    AuthService authService;

    @GetMapping("/changepassword")
    public String changePassword(Model model) {
        User user = authService.getCurrentUser().get();
        model.addAttribute("userId", user.getUserId());
        return "home/ChangePassword";
    }

    @PostMapping("/changepassword")
    public Object doiMatKhau(Model model, @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
            @RequestParam("userId") Integer userId) {
        Optional<User> optionalUser = userDao.findById(userId);

        if (oldPassword.equals(optionalUser.get().getPassword())) {
            if (newPassword.equals(confirmPassword)) {
                User user = optionalUser.get();
                user.setPassword(confirmPassword);
                userDao.save(user);
                return ResponseEntity.ok("<script>alert('Chúc mừng bạn đã đổi mật khẩu thành công!');"
                        + "window.location.href='/';</script>");
            } else {
                model.addAttribute("message", "Mật khẩu mới với xác nhận mật khẩu không trùng khớp!");
                User user = (User) session.getAttribute("user");
                model.addAttribute("userId", user.getUserId());
                return "home/ChangePassword";
            }
        } else {
            model.addAttribute("message", "Mật khẩu cũ không chính xác!");
            User user = (User) session.getAttribute("user");
            model.addAttribute("userId", user.getUserId());
            return "home/ChangePassword";
        }
    }
}
