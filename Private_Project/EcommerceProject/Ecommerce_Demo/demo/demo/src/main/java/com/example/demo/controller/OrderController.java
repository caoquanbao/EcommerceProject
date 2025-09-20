package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import java.util.List;

import com.example.demo.model.Order;
import com.example.demo.model.Status;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // POST /orders → buyer tạo đơn hàng
    @PostMapping
    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<Order> createOrder(@RequestBody Order order,
                                             Authentication auth) {
        User user = (User) auth.getPrincipal();
        order.setBuyer(user);
        Order saved = orderService.createOrder(order);
        return ResponseEntity.ok(saved);
    }

    // GET /orders → xem đơn hàng
    @GetMapping
    public ResponseEntity<List<Order>> getOrders(Authentication auth) {
        User user = (User) auth.getPrincipal();
        List<Order> orders = orderService.getOrdersByUser(user);
        return ResponseEntity.ok(orders);
    }

    // PUT /orders/{id} → seller cập nhật trạng thái
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id,
                                                   @RequestParam Status status) {
        Order updated = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}
