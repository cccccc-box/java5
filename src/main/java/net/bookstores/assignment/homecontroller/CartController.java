package net.bookstores.assignment.homecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bookstores.assignment.entities.Cart;
import net.bookstores.assignment.service.CartService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public List<Cart> getCartByUserId(@PathVariable Integer userId) {
        return cartService.getCartByUserId(userId);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<Cart> updateCartItem(
            @PathVariable Integer cartId,
            @RequestBody Cart updatedCart) {
        Cart cart = cartService.updateCartItem(cartId, updatedCart.getAmount());
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Integer cartId) {
        cartService.deleteCartItem(cartId);
        return ResponseEntity.noContent().build();
    }
}
