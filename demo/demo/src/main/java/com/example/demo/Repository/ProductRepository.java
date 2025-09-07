package com.demo.repository;

import com.demo.model.Product;
import com.demo.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Lấy sản phẩm theo seller
    List<Product> findBySellerId(Long sellerId);

    // Lấy sản phẩm theo category
    List<Product> findByCategory(ProductCategory category);
}
