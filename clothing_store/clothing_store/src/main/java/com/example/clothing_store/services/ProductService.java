package com.example.clothing_store.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clothing_store.models.Product;
import com.example.clothing_store.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

// Service class to handle related operations
@Service
public class ProductService {
    // Add ProductRepository to interact with the database
    @Autowired
    private ProductRepository productRepository;

    // Save a new product to the database
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Display all products from the database
    public List<Product> findAll() {
        List<Product> productToReturn = new ArrayList<>();
        productToReturn = productRepository.findAll();
        return productToReturn;
    }

    // Delete a product by its ID
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found"); // Throw exception if product doesn't exist
        }
        productRepository.deleteById(id);
    }

    // Update an existing product by its ID
    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
            .map(product -> {
                product.setName(updatedProduct.getName()); // Update product name
                product.setDescription(updatedProduct.getDescription()); // Update product description
                product.setPrice(updatedProduct.getPrice()); // Update product price
                product.setCategory(updatedProduct.getCategory()); // Update associated category
                return productRepository.save(product); // Save updated product
            })
            .orElse(null); // Return null if product is not found
    }
}

