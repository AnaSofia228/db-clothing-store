package com.example.clothing_store.models;

import jakarta.persistence.*;

// Define this class as a JPA entity
@Entity
// Map this entity to the "order_items" table in the database
@Table(name = "order_items")
public class OrderItem {
    
    // Define the primary key with auto-increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Establish a many-to-one relationship with Order
    // "order_id" is the foreign key referencing the Order entity
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Establish a many-to-one relationship with Product
    // "product_id" is the foreign key referencing the Product entity
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Define a required column "quantity" to store the number of products in the order
    @Column(nullable = false)
    private Integer quantity;

    // Define a required column "price" to store the price of the product in the order
    @Column(nullable = false)
    private Double price;

    // Getter and setter for order
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // Getter and setter for product
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // Getter and setter for quantity
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Getter and setter for price
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // Getter and setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
