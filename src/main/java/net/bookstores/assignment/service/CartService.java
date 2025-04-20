package net.bookstores.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bookstores.assignment.dao.CartDao;
import net.bookstores.assignment.entities.Book;
import net.bookstores.assignment.entities.Cart;
import net.bookstores.assignment.entities.User;

@Service
public class CartService {
    @Autowired
    CartDao cartDao;

    public void addToCart(User user, Book book, int amount) {
        Cart cart = Cart.builder()
                .user(user)
                .book(book)
                .amount(amount)
                .build();
        cartDao.save(cart);
    }

    public List<Cart> getCartByUser(User user) {
        return cartDao.findByUser(user);
    }

    public void removeCartItem(Integer id) {
        cartDao.deleteById(id);
    }

    public void clearCart(User user) {
        List<Cart> carts = cartDao.findByUser(user);
        cartDao.deleteAll(carts);
    }
}
