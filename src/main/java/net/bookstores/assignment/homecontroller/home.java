package net.bookstores.assignment.homecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.bookstores.assignment.dao.AuthorDao;
import net.bookstores.assignment.dao.BookDao;
import net.bookstores.assignment.entities.Author;
import net.bookstores.assignment.entities.Book;

@Controller
public class Home {
    @Autowired
    BookDao bookDao;
    @Autowired
    AuthorDao authorDao;

    @GetMapping("/")
    public String index(Model model) {
        Pageable topFive = PageRequest.of(0, 5);
        List<Book> top5 = bookDao.findTop5Books(topFive);
        model.addAttribute("top5", top5);

        Pageable topTen = PageRequest.of(0, 10);
        List<Book> top10 = bookDao.find10RandomBooks(topTen);
        model.addAttribute("top10", top10);

        Pageable topFiveAuthor = PageRequest.of(0, 5);
        List<Author> top5Author = authorDao.findRandom5Authors(topFiveAuthor);
        model.addAttribute("top5Author", top5Author);
        return "/home/index";
    }

    @GetMapping("/testheader")
    public String testheader() {
        return "/home/testHeader";
    }
}
