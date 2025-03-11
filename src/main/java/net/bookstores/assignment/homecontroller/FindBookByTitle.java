package net.bookstores.assignment.homecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.bookstores.assignment.dao.BookDao;
import net.bookstores.assignment.entities.Book;

@Controller
public class FindBookByTitle {
    @Autowired
    BookDao bookDao;

    @GetMapping("/search")
    public String timSachTheoTen(@RequestParam("title") String title, Model model) {
        String tenSach = title.toLowerCase();
        List<Book> books = bookDao.findByTitleContaining(tenSach);
        model.addAttribute("books", books);
        return "home/findbook";
    }

}
