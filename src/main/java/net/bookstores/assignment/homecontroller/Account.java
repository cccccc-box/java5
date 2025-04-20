package net.bookstores.assignment.homecontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import jakarta.servlet.http.HttpSession;
import net.bookstores.assignment.dao.OrderDao;
import net.bookstores.assignment.dao.UserDao;
import net.bookstores.assignment.entities.Order;
import net.bookstores.assignment.entities.User;
import net.bookstores.assignment.service.AuthService;
import net.bookstores.assignment.service.UserService;

@Controller
@RequestMapping("/account")
public class Account {
    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    OrderDao orderDao;
    @Autowired
    HttpSession session;

    @GetMapping("/index")
    public String trangCaNhanh(Model model) {
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Vui lòng đăng nhập"));
        model.addAttribute("user", user);
        return "home/account";
    }

    @GetMapping("/edit")
    public String chinhSuaThongTin(Model model) {
        User user = authService.getCurrentUser()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Vui lòng đăng nhập"));
        model.addAttribute("user", user);
        return "home/editprofile";
    }

    @PostMapping("/update")
    public String capNhatThongTin(@RequestParam("userId") Integer userId, @RequestParam("address") String address,
            @RequestParam("fullName") String fullName, @RequestParam("email") String email,
            @RequestParam("phone") String phone) {
        Optional<User> userOptional = userDao.findById(userId);
        User user = userOptional.get();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        userDao.save(user);
        userService.updateUser(userId, fullName, email, phone, address);
        return "redirect:/account/index";
    }

    @GetMapping("/order")
    public String order(Model model) {
        User user = (User) session.getAttribute("user");
        Integer id = user.getUserId();
        List<Order> orders = orderDao.findOrdersByUserId(id);
        model.addAttribute("orders", orders);
        return "home/order";
    }

}
