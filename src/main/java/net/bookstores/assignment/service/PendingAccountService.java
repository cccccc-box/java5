package net.bookstores.assignment.service;

import net.bookstores.assignment.dao.UserDao;
import net.bookstores.assignment.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PendingAccountService {
    private final Map<String, PendingAccount> pendingAccounts = new HashMap<>();

    @Autowired
    private UserDao userDao;

    @Autowired
    private HttpSession session;

    public static class PendingAccount {
        public Integer userId;
        public LocalDateTime expiryTime;
        public String verificationCode;

        public PendingAccount(Integer userId, LocalDateTime expiryTime, String verificationCode) {
            this.userId = userId;
            this.expiryTime = expiryTime;
            this.verificationCode = verificationCode;
        }
    }

    public String createPendingAccount(User user) {
        String verificationCode = UUID.randomUUID().toString();
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(15);

        pendingAccounts.put(verificationCode, new PendingAccount(
                user.getUserId(),
                expiryTime,
                verificationCode));

        // Lưu vào session để có thể truy cập sau
        session.setAttribute("pendingVerificationCode", verificationCode);

        return verificationCode;
    }

    public boolean verifyAccount(String verificationCode) {
        PendingAccount pending = pendingAccounts.get(verificationCode);
        if (pending == null || LocalDateTime.now().isAfter(pending.expiryTime)) {
            return false;
        }

        // Kích hoạt tài khoản
        User user = userDao.findById(pending.userId).orElse(null);
        if (user != null) {
            user.setActive(true);
            userDao.save(user);
        }

        // Xóa khỏi danh sách chờ
        pendingAccounts.remove(verificationCode);
        session.removeAttribute("pendingVerificationCode");

        return true;
    }

    @Scheduled(fixedRate = 60000) // Chạy mỗi phút
    public void cleanupExpiredAccounts() {
        LocalDateTime now = LocalDateTime.now();
        pendingAccounts.entrySet().removeIf(entry -> {
            if (now.isAfter(entry.getValue().expiryTime)) {
                // Xóa tài khoản từ database nếu hết hạn
                userDao.deleteById(entry.getValue().userId);
                return true;
            }
            return false;
        });
    }
}