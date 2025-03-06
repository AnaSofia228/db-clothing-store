package com.example.clothing_store.models;

import jakarta.persistence.*;

// Define this class as a JPA entity
@Entity
// Map this entity to the "products" table in the database
@Table(name = "products")
public class Product {
    
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

    // Define a required column "price" to store the product's price
    @Column(nullable = false)
    private Double price;

    // Define a required column "stock" to store the available stock of the product
    @Column(nullable = false)
    private Integer stock;

    // Establish a many-to-one relationship with Category
    // "category_id" is the foreign key referencing the Category entity
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Getter and setter for category
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // Getter and setter for stock
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    // Getter and setter for price
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

