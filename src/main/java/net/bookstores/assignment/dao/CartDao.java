package net.bookstores.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bookstores.assignment.entities.Cart;

public interface CartDao extends JpaRepository<Cart, Integer> {

}
