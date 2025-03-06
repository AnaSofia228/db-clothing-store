package com.example.clothing_store.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

// Define this class as a JPA entity
@Entity
// Map this entity to the "orders" table in the database
@Table(name = "orders")
public class Order {
    
    // Define the primary key with auto-increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Define a required column "orderDate"
    @Column(nullable = false)
    private String orderDate;

    // Define a required column "totalAmount" to store the total price of the order
    @Column(nullable = false)
    private Double totalAmount;

    // Establish a many-to-one relationship with Customer
    // "customer_id" is the foreign key referencing the Customer entity
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // Establish a one-to-many relationship with OrderItem
    // "mappedBy" indicates that the "order" field in OrderItem owns the relationship
    // "cascade = CascadeType.REMOVE, orphanRemoval = true" ensures that when an order is deleted, all its items are deleted as well
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore // Prevent infinite recursion during JSON serialization
    private List<OrderItem> orderItems;

    // Getter and setter for orderItems
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    // Getter and setter for customer
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Getter and setter for totalAmount
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Getter and setter for orderDate
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    // Getter and setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

