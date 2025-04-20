package net.bookstores.assignment.homecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {
    @Autowired
    HttpSession session;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        session.removeAttribute("securityUri");
        session.removeAttribute("user");

        Cookie userCookie = new Cookie("user", null); // Đặt giá trị thành null
        userCookie.setMaxAge(0); // Đặt tuổi tối đa thành 0 để xóa
        userCookie.setPath("/"); // Đảm bảo đường dẫn giống với cookie ban đầu
        userCookie.setHttpOnly(true); // Giữ nguyên thuộc tính HttpOnly (nếu có)
        response.addCookie(userCookie);
        return "redirect:/login";
    }
}
