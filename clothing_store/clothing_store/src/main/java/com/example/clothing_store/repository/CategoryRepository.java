package com.example.clothing_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.clothing_store.models.Category;

// Mark this interface as a Spring Data repository
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

} 
