package net.bookstores.assignment.homecontroller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;
import net.bookstores.assignment.dao.BookDao;
import net.bookstores.assignment.dao.OrderDao;
import net.bookstores.assignment.dao.OrderDetailDao;
import net.bookstores.assignment.dao.PaymentMethodDao;
import net.bookstores.assignment.dao.ShippingProviderDao;
import net.bookstores.assignment.entities.Book;
import net.bookstores.assignment.entities.Order;
import net.bookstores.assignment.entities.OrderDetail;
import net.bookstores.assignment.entities.PaymentMethod;
import net.bookstores.assignment.entities.ShippingProvider;
import net.bookstores.assignment.entities.User;
import net.bookstores.assignment.service.AuthService;
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
    BookDao bookDao;
    @Autowired
    UserService userService;
    @Autowired
    ShippingProviderDao shippingProviderDao;
    @Autowired
    PaymentMethodDao paymentMethodDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderDetailDao orderDetailDao;
    @Autowired
    HttpServletRequest request;
    @Autowired
    AuthService authService;

    @GetMapping("/buy/{bookId}")
    public String buy(@PathVariable("bookId") Integer bookId, Model model) {
        model.addAttribute("book", bookService.findById(bookId));
        model.addAttribute("user", authService.getCurrentUser().get());
        model.addAttribute("shippingProviders", shippingProviderDao.findByIsActiveTrue());
        model.addAttribute("paymentMethods", paymentMethodDao.findByActiveTrue());
        return "home/buy";
    }

    @PostMapping("buy/checkout")
    public String checkOut(@RequestParam("fullName") String Name,
            @RequestParam("phone") String phone, @RequestParam("address") String address,
            @RequestParam("bookId") Integer bookId, @RequestParam("product1") Integer soLuongMua,
            @RequestParam("payment-method") Integer thanhToan,
            @RequestParam("shipping-provider") Integer donViVanChuyen) {

        Integer userId = authService.getCurrentUserId();

        User u = User.builder().userId(userId).build();
        Book book = bookService.findById(bookId);
        Float giaSachSauGiam = book.getDiscountedPrice();
        ShippingProvider shippingProvider = shippingProviderDao.findById(donViVanChuyen).get();
        Float tongTien = shippingProvider.getShippingFee() + giaSachSauGiam * soLuongMua;
        PaymentMethod paymentMethod = paymentMethodDao.findById(thanhToan).get();

        if (paymentMethod.getPaymentMethodID() == 1) {
            Order order = Order.builder().user(u).recipientName(Name).phone(phone).address(address)
                    .orderDate(new Date())
                    .totalMerchandiseValue(giaSachSauGiam * soLuongMua).totalAmount(tongTien)
                    .paymentMethod(paymentMethod)
                    .shippingProvider(shippingProvider).status(0).build();
            orderDao.save(order); // thêm vào table order

            OrderDetail orderDetail = OrderDetail.builder().order(order).book(book).quantity(soLuongMua)
                    .price(book.getPrice())
                    .discountedPrice(giaSachSauGiam).build();
            orderDetailDao.save(orderDetail);

            Integer soLuongSach = book.getQuantity();

            book.setQuantity(soLuongSach - soLuongMua);
            bookDao.save(book);
        }
        return "redirect:/account/orders";
    }

}
