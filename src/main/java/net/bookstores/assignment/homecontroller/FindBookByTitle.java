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
    public String timSachTheoTen(@RequestParam("title") String title,
            @RequestParam(name = "sort", required = false) String sort,
            Model model) {
        String tenSach = title.toLowerCase();
        List<Book> books = bookDao.findByTitleContaining(tenSach);

        // Xử lý sắp xếp theo giá giảm/giá tăng
        if ("asc".equals(sort)) {
            books.sort((b1, b2) -> Double.compare(b1.getDiscountedPrice(), b2.getDiscountedPrice()));
        } else if ("desc".equals(sort)) {
            books.sort((b1, b2) -> Double.compare(b2.getDiscountedPrice(), b1.getDiscountedPrice()));
        }

        model.addAttribute("books", books);
        return "home/findbook"; // trang hiển thị kết quả tìm
    }

}
