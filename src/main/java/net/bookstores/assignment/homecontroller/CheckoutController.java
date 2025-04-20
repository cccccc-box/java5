// package net.bookstores.assignment.homecontroller;

// import java.util.Date;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import net.bookstores.assignment.entities.Cart;
// import net.bookstores.assignment.entities.Order;
// import net.bookstores.assignment.entities.User;
// import net.bookstores.assignment.service.CartService;
// import net.bookstores.assignment.service.OrderService;
// import net.bookstores.assignment.service.UserService;

// @RestController
// @RequestMapping("/api/cart")
// public class CheckoutController {

//     @Autowired
//     private CartService cartService;

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private OrderService orderService;

//     // Đặt hàng
//     @PostMapping("/checkout")
//     public String checkout(@RequestParam String address) {
//         Optional<User> userOpt = userService.readCookie();
//         if (userOpt.isEmpty()) return "Không tìm thấy người dùng!";

//         User user = userOpt.get();
//         List<Cart> cartItems = cartService.getCartByUser(user);
//         if (cartItems.isEmpty()) return "Giỏ hàng rỗng!";

//         for (Cart cart : cartItems) {
//             Order order = Order.builder()
//                     .user(user)
//                     .book(cart.getBook())
//                     .amount(cart.getAmount())
//                     .price(cart.getBook().getPrice())
//                     .discountPercentage(0f)
//                     .discountedPrice(cart.getBook().getPrice() * cart.getAmount())
//                     .address(address)
//                     .active(true)
//                     .createDate(new Date())
//                     .build();
//             orderService.save(order);
//         }

//         // Sau khi đặt hàng, xoá giỏ hàng
//         cartService.clearCart(user);

//         return "Đặt hàng thành công!";
//     }
// }
