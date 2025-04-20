package net.bookstores.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bookstores.assignment.dao.OrderDao;
import net.bookstores.assignment.entities.Order;

@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    public List<Order> findAll() {
        return orderDao.findAll();
    }

    public Optional<Order> findById(Integer id) {
        return orderDao.findById(id);
    }
    public Order save(Order order) {
        return orderDao.save(order);
    }
}
