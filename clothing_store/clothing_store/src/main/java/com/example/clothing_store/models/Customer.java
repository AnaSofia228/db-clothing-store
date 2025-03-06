package com.example.clothing_store.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

// Define this class as a JPA entity
@Entity
// Map this entity to the "customers" table in the database
@Table(name = "customers")
public class Customer {
    
    // Define the primary key with auto-increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Define a required column "name" 
    @Column(nullable = false, length = 100)
    private String name;

    // Define a required column "email"
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    // Define a required column "phone" 
    @Column(nullable = false, length = 15)
    private String phone;

    // Define a required column "lastname"
    @Column(nullable = false, length = 15)
    private String lastname;

    // Establish a one-to-many relationship with Order
    // "mappedBy" indicates that the "customer" field in the Order entity owns the relationship
    // "cascade = CascadeType.REMOVE" means that if a customer is deleted, its orders will be deleted as well
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnore // Prevent infinite recursion during JSON serialization
    private List<Order> orders;

    // Getter and setter for lastname
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // Getter and setter for orders
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    // Getter and setter for phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter and setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
