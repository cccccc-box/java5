package net.bookstores.assignment.homecontroller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import net.bookstores.assignment.dao.BookDao;
import net.bookstores.assignment.dao.OrderDao;
import net.bookstores.assignment.dao.UserDao;
import net.bookstores.assignment.entities.Book;
import net.bookstores.assignment.entities.Order;
import net.bookstores.assignment.entities.User;

@Controller
public class BuyProduct {
    @Autowired
    HttpSession session;
    @Autowired
    BookDao bookDao;
    @Autowired
    UserDao userDao;
    @Autowired
    OrderDao orderDao;

    @PostMapping("/buy")
    public Object buy(@RequestParam("bookId") Integer bookId) {
        Optional<Book> bookOptional = bookDao.findById(bookId);
        Book book = bookOptional.get();
        User user = (User) session.getAttribute("user");

        // thêm vào bảng Orders
        // Order order =
        // Order.builder().user(user).book(book).price(book.getPrice()).amount(1)
        // .discountPercentage(book.getDiscountPercentage()).discountedPrice(book.getDiscountedPrice())
        // .address(user.getAddress()).createDate(new Date()).build();
        // orderDao.save(order);
        // return ResponseEntity.ok("<script>alert('Mua hàng thành công!');"
        // + "window.location.href='/';</script>");
        return "home/buy";
    }

}
