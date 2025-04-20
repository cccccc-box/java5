package net.bookstores.assignment.homecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.bookstores.assignment.dao.OrderDao;
import net.bookstores.assignment.dao.OrderDetailDao;
import net.bookstores.assignment.entities.Order;
import net.bookstores.assignment.entities.OrderDetail;
import net.bookstores.assignment.service.AuthService;

@Controller
public class OrderTrackingController {
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderDetailDao orderDetailDao;
    @Autowired
    AuthService authService;

    @GetMapping("/account/orders")
    public String order(Model model) {
        Integer userId = authService.getCurrentUserId();
        model.addAttribute("listCXN", orderDao.findOrdersByUserIdAndStatusZero(userId));
        model.addAttribute("listDGH", orderDao.findOrdersByUserIdAndStatusShipping(userId));
        model.addAttribute("listDG", orderDao.findOrdersByUserIdAndStatusDelivered(userId));
        model.addAttribute("listHD", orderDao.findOrdersByUserIdAndStatusCancelled(userId));
        return "home/order";
    }

    @GetMapping("/account/orders/{orderId}")
    public String orderDetail(Model model, @PathVariable Integer orderId) {
        Order order = orderDao.findById(orderId).get();
        model.addAttribute("order", order);
        List<OrderDetail> orderDetail = orderDetailDao.findByOrderId(orderId);
        model.addAttribute("listDetail", orderDetail);
        return "home/order-detail";
    }

    @GetMapping("/account/orders/cancel/{orderId}")
    public String cancel(Model model, @PathVariable Integer orderId) {
        Order order = orderDao.findById(orderId).get();
        order.setStatus(3);
        orderDao.save(order);
        return "redirect:/account/orders";
    }

    @GetMapping("/account/orders/delivered/{orderId}")
    public String delivered(Model model, @PathVariable Integer orderId) {
        Order order = orderDao.findById(orderId).get();
        order.setStatus(2);
        orderDao.save(order);
        return "redirect:/account/orders";
    }

}
