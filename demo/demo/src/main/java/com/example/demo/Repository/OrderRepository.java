package com.demo.repository;

import com.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Lấy tất cả order của buyer
    List<Order> findByBuyerId(Long buyerId);
}
