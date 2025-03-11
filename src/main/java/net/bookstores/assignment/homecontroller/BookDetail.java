package net.bookstores.assignment.homecontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.bookstores.assignment.dao.BookDao;
import net.bookstores.assignment.entities.Book;

@Controller
public class BookDetail {
    @Autowired
    BookDao bookDao;

    @GetMapping("/bookdetail")
    public String bookdetail(Model model, @RequestParam("id") Integer id) {
        Optional<Book> book = bookDao.findById(id);
        model.addAttribute("book", book.get());
        return "home/book";
    }
}
