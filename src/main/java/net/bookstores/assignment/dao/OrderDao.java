package net.bookstores.assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.bookstores.assignment.entities.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o ORDER BY o.orderId DESC")
    Order findLastOrder();

    @Query("SELECT o FROM Order o WHERE o.user.userId = :userId")
    List<Order> findOrdersByUserId(@Param("userId") Integer userId);

}
