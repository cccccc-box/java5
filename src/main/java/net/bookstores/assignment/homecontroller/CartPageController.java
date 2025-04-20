package net.bookstores.assignment.homecontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.bookstores.assignment.entities.Cart;
import net.bookstores.assignment.entities.Order;
import net.bookstores.assignment.entities.User;
import net.bookstores.assignment.service.CartService;
import net.bookstores.assignment.service.OrderService;
import net.bookstores.assignment.service.UserService;

@Controller
@RequestMapping("/home/cart")
public class CartPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String viewCart(Model model) {
        Optional<User> userOpt = userService.readCookie();
        if (userOpt.isPresent()) {
            List<Cart> cartItems = cartService.getCartByUser(userOpt.get());
            model.addAttribute("cartItems", cartItems);
        } else {
            model.addAttribute("cartItems", List.of());
        }
        return "home/cart"; // Trả về trang cart.html (Thymeleaf)
    }

    @PostMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable Integer id) {
        cartService.removeCartItem(id);
        return "redirect:/home/cart";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam("address") String address) {
        Optional<User> userOpt = userService.readCookie();
        if (userOpt.isEmpty()) return "redirect:/home/cart";

        User user = userOpt.get();
        List<Cart> cartItems = cartService.getCartByUser(user);
        for (Cart cart : cartItems) {
            Order order = Order.builder()
                    .user(user)
                    .book(cart.getBook())
                    .amount(cart.getAmount())
                    .price(cart.getBook().getPrice())
                    .discountPercentage(0f)
                    .discountedPrice(cart.getBook().getPrice() * cart.getAmount())
                    .address(address)
                    .active(true)
                    .createDate(new java.util.Date())
                    .build();
            orderService.save(order);
        }
        cartService.clearCart(user);
        return "redirect:/home/cart";
    }
}
