package net.bookstores.assignment.homecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {
    @Autowired
    HttpSession session;

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("securityUri");
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
