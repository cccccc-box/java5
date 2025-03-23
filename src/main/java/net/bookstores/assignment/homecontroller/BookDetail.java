package net.bookstores.assignment.homecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.bookstores.assignment.Service.BookService;

@Controller
public class BookDetail {
    @Autowired
    BookService bookService;

    @GetMapping("/bookdetail")
    public String bookdetail(Model model, @RequestParam("id") Integer id) {
        model.addAttribute(bookService.detail(id));
        return "home/book";
    }
}
