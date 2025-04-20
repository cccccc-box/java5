package net.bookstores.assignment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bookstores.assignment.entities.Book;
import net.bookstores.assignment.entities.Cart;
import net.bookstores.assignment.entities.User;

public interface CartDao extends JpaRepository<Cart, Integer> {

    List<Cart> findByUser(User user);

    Optional<Cart> findByUserAndBook(User user, Book book);
}
