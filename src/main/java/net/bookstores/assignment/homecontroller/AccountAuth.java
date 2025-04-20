package net.bookstores.assignment.homecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.bookstores.assignment.service.PendingAccountService;

@Controller
public class AccountAuth {
    @Autowired
    private PendingAccountService pendingAccountService;

    @ResponseBody
    @GetMapping("/xacthuctaikhoan")
    public String xacThucTaiKhoan(@RequestParam("code") String verificationCode) {
        if (pendingAccountService.verifyAccount(verificationCode)) {
            return "<script>alert('Xác thực thành công! Bây giờ bạn đã có thể đăng nhập và mua hàng.');" +
                    "window.location.href='/login';</script>";
        } else {
            return "<script>alert('Xác thực không thành công! Bạn vui lòng đăng ký lại tài khoản.');" +
                    "window.location.href='/signin';</script>";
        }
    }
}
