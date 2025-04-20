package net.bookstores.assignment.homecontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.bookstores.assignment.entities.Book;
import net.bookstores.assignment.entities.User;
import net.bookstores.assignment.service.BookService;
import net.bookstores.assignment.service.CartService;
import net.bookstores.assignment.service.UserService;
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addToCart(@RequestParam("bookId") Integer bookId,
                            @RequestParam("amount") Integer amount) {

        Optional<User> userOpt = userService.readCookie();
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Book book = bookService.findById(bookId);
            cartService.addToCart(user, book, amount);
            return "redirect:/cart"; // chuyển đến trang giỏ hàng
        }

        return "redirect:/login"; // nếu người dùng chưa đăng nhập
    }
}
