package com.example.clothing_store.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.clothing_store.models.Category;
import com.example.clothing_store.services.CategoryService;

import jakarta.persistence.EntityNotFoundException;

// The REST class controller handles HTTP requests
@RestController
// Assign requests to "/categories"
@RequestMapping("/categories")
public class CategoryController {
    // Automatically add a CategoryService instance
    @Autowired 
    private CategoryService categoryService;

    // HTTP POST to create a new category
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    // HTTP GET request to retrieve all categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    // HTTP DELETE request to delete a category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            // Try to delete the categories by ID, calling the service layer method
            categoryService.deleteCategoryById(id); // Call and look for the service to remove the category
            return ResponseEntity.ok("Category with id "+ id + " successfully removed");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
    }

    // HTTP PUT request to update an existing category by ID
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        Category category = categoryService.updateCategory(id, updatedCategory);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build(); // If the category does not exist, it returns status 404
    }
} 
