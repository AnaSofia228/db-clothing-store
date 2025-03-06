package com.example.clothing_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.clothing_store.models.Product;
import com.example.clothing_store.services.ProductService;

import jakarta.persistence.EntityNotFoundException;

// The REST class controller handles HTTP requests
@RestController
// Assign requests to "/product"
@RequestMapping("/product")
public class ProductController {
    // Automatically add a ProductService instance
    @Autowired 
    private ProductService productService;

    // HTTP POST to create a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // HTTP GET request to retrieve all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    // HTTP DELETE request to delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            // Attempt to delete the product by ID, calling the service layer method
            productService.deleteProductById(id);
            return ResponseEntity.ok("Product with id " + id + " successfully removed");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    } 

    // HTTP PUT request to update an existing product by ID
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product product = productService.updateProduct(id, updatedProduct);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build(); // If the product does not exist, it returns status 404
    }
}

