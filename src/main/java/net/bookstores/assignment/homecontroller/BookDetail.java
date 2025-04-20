package net.bookstores.assignment.homecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.bookstores.assignment.service.BookService;

@Controller
public class BookDetail {
    @Autowired
    BookService bookService;

    @GetMapping("/bookdetail/{id}")
    public String bookdetail(Model model, @PathVariable("id") Integer id) {
        model.addAttribute(bookService.detail(id));
        return "home/book";
    }
}
