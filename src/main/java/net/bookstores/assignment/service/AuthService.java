package net.bookstores.assignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.bookstores.assignment.entities.User;

@Service
public class AuthService {
    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

    public void createLoginSession(User user, HttpServletResponse response) {
        // Lưu thông tin user vào session
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userEmail", user.getEmail());
        session.setAttribute("userRole", user.getRole());

        // Tạo cookie session an toàn
        Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
        sessionCookie.setHttpOnly(true);
        sessionCookie.setSecure(true); // Chỉ HTTPS trong production
        sessionCookie.setPath("/");
        sessionCookie.setMaxAge(7 * 24 * 60 * 60); // 7 ngày
        response.addCookie(sessionCookie);
    }

    // Lưu URI hiện tại
    public void saveRequestUri(String uri) {
        session.setAttribute("securityUri", uri);
    }

    // Lấy ID user đăng nhập
    public Integer getCurrentUserId() {
        return (Integer) session.getAttribute("userId");
    }

    // Lấy toàn bộ thông tin user từ database
    public Optional<User> getCurrentUser() {
        Integer userId = getCurrentUserId();
        if (userId == null) {
            return Optional.empty();
        }
        return userService.getUserById(userId);
    }

    // Kiểm tra user có đăng nhập không
    public boolean isLoggedIn() {
        return getCurrentUserId() != null;
    }

    public boolean isAdminUserValid() {
        Optional<User> userOptional = this.getCurrentUser();
        if (userOptional.isEmpty() || !userOptional.get().getActive()) {
            session.invalidate();
            return false;
        }
        return userOptional.get().getRole();
    }

    // Lấy thông tin đơn giản từ session (không query DB)
    public User getCurrentUserFromSession() {
        Integer userId = getCurrentUserId();
        if (userId == null)
            return null;

        return User.builder()
                .userId(userId)
                .email((String) session.getAttribute("userEmail"))
                .role((Boolean) session.getAttribute("userRole"))
                .build();
    }

    public void logout() {
        // Xóa tất cả attributes trong session
        // session.removeAttribute("userId");
        // session.removeAttribute("userEmail");
        // session.removeAttribute("userRole");
        // session.removeAttribute("securityUri");
        // Hủy toàn bộ session
        session.invalidate();
    }
}