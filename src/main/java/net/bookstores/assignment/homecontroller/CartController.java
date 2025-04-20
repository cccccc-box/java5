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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
    @GetMapping("/cart")
    public String cart() {
        return "home/cart";
>>>>>>> f9372da62a339d8a30cfe73c019da87719361912
    }
}
