package net.bookstores.assignment.admincontroller;

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

@Controller
public class OrderController {
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderDetailDao orderDetailDao;

    @GetMapping("/admin/order/index")
    public String order(Model model) {
        model.addAttribute("listCXN", orderDao.findOrdersByStatusZero());
        model.addAttribute("listDGH", orderDao.findOrdersByStatusShipping());
        model.addAttribute("listDG", orderDao.findOrdersByStatusDelivered());
        model.addAttribute("listHD", orderDao.findOrdersByStatusCancelled());
        return "admin/order/index";
    }

    @GetMapping("/admin/order/index/{status}")
    public String orderByStatus(@PathVariable int status, Model model) {
        model.addAttribute("listCXN", orderDao.findOrdersByStatusZero());
        model.addAttribute("listDGH", orderDao.findOrdersByStatusShipping());
        model.addAttribute("listDG", orderDao.findOrdersByStatusDelivered());
        model.addAttribute("listHD", orderDao.findOrdersByStatusCancelled());
        model.addAttribute("activeTab", status);
        return "admin/order/index";
    }

    @GetMapping("/admin/order/update/1/{orderId}")
    public String updateDGH(@PathVariable("orderId") Integer orderId) {
        Order order = orderDao.findById(orderId).get();
        order.setStatus(1);
        orderDao.save(order);
        return "redirect:/admin/order/index/1";
    }

    @GetMapping("/admin/order/update/2/{orderId}")
    public String updateDG(@PathVariable("orderId") Integer orderId) {
        Order order = orderDao.findById(orderId).get();
        order.setStatus(2);
        orderDao.save(order);
        return "redirect:/admin/order/index/2";
    }

    @GetMapping("/admin/order/update/3/{orderId}")
    public String updateHD(@PathVariable("orderId") Integer orderId) {
        Order order = orderDao.findById(orderId).get();
        order.setStatus(3);
        orderDao.save(order);
        return "redirect:/admin/order/index/3";
    }

    @GetMapping("/admin/order/detail/{orderId}")
    public String orderDetail(@PathVariable("orderId") Integer orderId, Model model) {
        Order order = orderDao.findById(orderId).get();
        model.addAttribute("order", order);
        List<OrderDetail> orderDetail = orderDetailDao.findByOrderId(orderId);
        model.addAttribute("listDetail", orderDetail);
        return "admin/order/detail";
    }

}
