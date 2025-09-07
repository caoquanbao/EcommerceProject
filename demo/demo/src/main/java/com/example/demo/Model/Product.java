package com.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer stock;

    @Enumerated(EnumType.STRING)
    private ProductCategory category; // food, drink, snack

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;
}

// Enum category
enum ProductCategory {
    FOOD,
    DRINK,
    SNACK
}
