package net.bookstores.assignment.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.bookstores.assignment.entities.User;
import net.bookstores.assignment.service.UserService;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        session.setAttribute("securityUri", uri);
        Cookie[] cookies = request.getCookies();
        Integer userId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())) { // Tìm cookie có tên "user"
                    userId = Integer.parseInt(cookie.getValue()); // Lấy giá trị cookie (User ID)
                }
            }
        }

        Optional<User> userCookie;
        User user = null;

        if (userId != null) {
            userCookie = userService.getUserById(userId);
            user = userCookie.get();
        }

        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }
        if (uri.startsWith("/admin") && !user.getRole()) {
            session.setAttribute("message", "Tài khoản của bạn không có quyền truy cập!");
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
