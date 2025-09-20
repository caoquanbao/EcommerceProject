package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.Status;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public Order createOrder(Order order) {
        // TODO: lưu order vào database
        return order;
    }

    public List<Order> getOrdersByUser(User user) {
        // TODO: query từ DB dựa trên user
        return List.of();
    }

    public Order updateOrderStatus(Long orderId, Status status) {
        // TODO: tìm order theo orderId và set status
        Order order = new Order();
        order.setStatus(status);
        return order;
    }
}
