package net.bookstores.assignment.homecontroller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.bookstores.assignment.service.BookService;
import net.bookstores.assignment.service.OrderService;
import net.bookstores.assignment.service.UserService;

@Controller
public class BuyProduct {
    @Autowired
    OrderService orderService;
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @GetMapping("/buy")
    public String buy(@RequestParam("bookId") Integer bookId, Model model) {
        model.addAttribute("book", bookService.findById(bookId));
        model.addAttribute("user", userService.readCookie().get());
        return "home/buy";
    }

}
