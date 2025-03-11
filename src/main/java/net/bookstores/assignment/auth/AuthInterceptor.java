package net.bookstores.assignment.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.bookstores.assignment.entities.User;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        session.setAttribute("securityUri", uri);
        User t = (User) session.getAttribute("user");
        if (t == null) {
            response.sendRedirect("/login");
            return false;
        }
        if (uri.startsWith("/admin") && !t.getRole()) {
            session.setAttribute("message", "Tài khoản của bạn không có quyền truy cập!");
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
