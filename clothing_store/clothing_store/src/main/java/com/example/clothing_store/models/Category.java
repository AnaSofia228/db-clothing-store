package com.example.clothing_store.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

// Define this class as a JPA entity
@Entity
// Map this entity to the "categories" table in the database
@Table(name = "categories")
public class Category {
    
    // Define the primary key with auto-increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Define a required column "name" 
    @Column(nullable = false, length = 100)
    private String name;

    // Define a required column "description" 
    @Column(nullable = false, length = 255)
    private String description;

    // Establish a one-to-many relationship with Product
    // "mappedBy" indicates that the "category" field in the Product entity owns the relationship
    // "cascade = CascadeType.REMOVE" means that if a category is deleted, its products will be deleted as well
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    @JsonIgnore // Prevent infinite recursion during JSON serialization
    private List<Product> products;

    // Getter and setter for products
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // Getter and setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
