package net.bookstores.assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.bookstores.assignment.entities.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o ORDER BY o.orderId DESC")
    Order findLastOrder();

    @Query("SELECT o FROM Order o WHERE o.user.userId = :userId AND o.status = 0")
    List<Order> findOrdersByUserIdAndStatusZero(@Param("userId") Integer userId);

    @Query("SELECT o FROM Order o WHERE o.user.userId = :userId AND o.status = 1")
    List<Order> findOrdersByUserIdAndStatusShipping(@Param("userId") Integer userId);

    @Query("SELECT o FROM Order o WHERE o.user.userId = :userId AND o.status = 2")
    List<Order> findOrdersByUserIdAndStatusDelivered(@Param("userId") Integer userId);

    @Query("SELECT o FROM Order o WHERE o.user.userId = :userId AND o.status = 3")
    List<Order> findOrdersByUserIdAndStatusCancelled(@Param("userId") Integer userId);

    @Query("SELECT o FROM Order o WHERE o.status = 0")
    List<Order> findOrdersByStatusZero();

    @Query("SELECT o FROM Order o WHERE o.status = 1")
    List<Order> findOrdersByStatusShipping();

    @Query("SELECT o FROM Order o WHERE o.status = 2")
    List<Order> findOrdersByStatusDelivered();

    @Query("SELECT o FROM Order o WHERE o.status = 3")
    List<Order> findOrdersByStatusCancelled();

}
