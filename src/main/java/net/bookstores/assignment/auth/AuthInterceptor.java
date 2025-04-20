package net.bookstores.assignment.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.bookstores.assignment.service.AuthService;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();

        // Lưu URI hiện tại nếu cần redirect sau login
        if (!uri.equals("/login")) {
            authService.saveRequestUri(uri);
        }

        // Kiểm tra đăng nhập
        if (!authService.isLoggedIn()) {
            response.sendRedirect("/login");
            return false;
        }

        // Kiểm tra quyền admin cho các route /admin
        if (uri.startsWith("/admin")) {
            if (!authService.isAdminUserValid()) {
                request.getSession().setAttribute("message", "Bạn không có quyền truy cập vào trang admin");
                response.sendRedirect("/login");
                return false;
            }
        }

        return true;
    }
}