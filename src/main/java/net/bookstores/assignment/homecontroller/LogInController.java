package net.bookstores.assignment.homecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.bookstores.assignment.dao.UserDao;
import net.bookstores.assignment.entities.User;
import net.bookstores.assignment.service.AuthService;

@Controller
public class LogInController {
    @Autowired
    UserDao userDao;

    @Autowired
    AuthService authService;

    @Autowired
    HttpSession session;

    @GetMapping("/login")
    public String login(Model model) {
        if (session.getAttribute("message") != null) {
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
        }
        return "home/login";
    }

    @PostMapping("/login/check")
    public String checkUser(Model model, HttpServletResponse response, RedirectAttributes redirectAttributes,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session) {
        User user = userDao.findByEmail(email);

        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Tài khoản không tồn tại");
            return "redirect:/login";
        }

        if (!user.getActive()) {
            redirectAttributes.addFlashAttribute("message", "Tài khoản tạm thời bị khóa");
            return "redirect:/login";
        }

        if (!user.getPassword().equals(password)) {
            redirectAttributes.addFlashAttribute("message", "Mật khẩu chưa chính xác");
            return "redirect:/login";
        }

        authService.createLoginSession(user, response);

        String securityUri = (String) session.getAttribute("securityUri");
        if (securityUri != null) {
            return "redirect:" + securityUri;
        } else {
            return user.getRole() ? "redirect:/admin/user/index" : "redirect:/";
        }
    }

}
