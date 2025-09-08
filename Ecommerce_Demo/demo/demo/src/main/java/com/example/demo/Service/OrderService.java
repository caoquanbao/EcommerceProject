package com.demo.service;

import com.demo.model.Order;
import com.demo.model.OrderItem;
import com.demo.model.User;
import com.demo.repository.OrderItemRepository;
import com.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    // Buyer tạo order
    public Order createOrder(Order order) {
        // Tính total price từ items
        double total = 0;
        for (OrderItem item : order.getItems()) {
            total += item.getPrice() * item.getQuantity();
        }
        order.setTotalPrice(total);
        Order savedOrder = orderRepository.save(order);

        // Save items
        for (OrderItem item : order.getItems()) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }

        return savedOrder;
    }

    // Lấy tất cả order của buyer
    public List<Order> getOrdersByUser(User buyer) {
        return orderRepository.findByBuyerId(buyer.getId());
    }

    // Update status (seller)
    public Order updateOrderStatus(Long orderId, Enum status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus((Enum) status);
            return orderRepository.save(order);
        }
        return null; // hoặc throw exception
    }
}
