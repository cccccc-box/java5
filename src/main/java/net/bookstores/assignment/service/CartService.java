package net.bookstores.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bookstores.assignment.dao.CartDao;
import net.bookstores.assignment.entities.Cart;

@Service
public class CartService {
    @Autowired
    private CartDao cartDao;

    public List<Cart> getCartByUserId(Integer userId) {
        return cartDao.findByUserUserId(userId);
    }

    public Cart updateCartItem(Integer cartId, Integer amount) {
        Cart cart = cartDao.findById(cartId).orElseThrow(() -> new RuntimeException("Cart item not found"));
        cart.setAmount(amount);
        return cartDao.save(cart);
    }

    public void deleteCartItem(Integer cartId) {
        cartDao.deleteById(cartId);
    }
}
