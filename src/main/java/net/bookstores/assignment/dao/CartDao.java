package net.bookstores.assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.bookstores.assignment.entities.Cart;

public interface CartDao extends JpaRepository<Cart, Integer> {
    @Query("SELECT c FROM Cart c WHERE c.user.userId = :userId")
    List<Cart> findByUserUserId(Integer userId);

    void deleteByUserUserId(Integer userId);
}
