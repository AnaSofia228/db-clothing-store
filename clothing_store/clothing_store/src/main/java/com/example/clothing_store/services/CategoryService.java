package com.example.clothing_store.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clothing_store.models.Category;
import com.example.clothing_store.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

// Service class to handle related operations
@Service
public class CategoryService {
    // Add CategoryRepository to interact with the database
    @Autowired
    private CategoryRepository categoryRepository;

    // Save a new category to the database
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Display all categories in the database
    public List<Category> findAll() {
        List<Category> categoryToReturn = new ArrayList<>();
        categoryToReturn = categoryRepository.findAll();
        return categoryToReturn;
    }

    // Delete a category by its ID
    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found"); // Throw exception if category doesn't exist
        }
        categoryRepository.deleteById(id);
    }

    // Update an existing category by its ID
    public Category updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id)
            .map(category -> {
                category.setName(updatedCategory.getName()); // Update category name
                category.setDescription(updatedCategory.getDescription()); // Update category description
                return categoryRepository.save(category); // Save updated category
            })
            .orElse(null); // Return null if category is not found
    }
}
