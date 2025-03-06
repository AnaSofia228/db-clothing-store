package com.example.clothing_store.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clothing_store.models.OrderItem;
import com.example.clothing_store.repository.OrderItemRepository;

import jakarta.persistence.EntityNotFoundException;

// Service class to handle related operations
@Service
public class OrderItemService {  
    // Add OrderItemRepository to interact with the database
    @Autowired
    private OrderItemRepository orderItemRepository;

    // Save a new order item to the database
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Display all order items from the database
    public List<OrderItem> findAll() {
        List<OrderItem> orderItemToReturn = new ArrayList<>();
        orderItemToReturn = orderItemRepository.findAll();
        return orderItemToReturn;
    }

    // Delete an order item by its ID
    public void deleteOrderItemById(Long id) {
        if (!orderItemRepository.existsById(id)) {
            throw new EntityNotFoundException("Order item not found"); // Throw exception if order item doesn't exist
        }
        orderItemRepository.deleteById(id);
    }

    // Update an existing order item by its ID
    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        return orderItemRepository.findById(id)
            .map(orderItem -> {
                orderItem.setOrder(updatedOrderItem.getOrder()); // Update associated order
                orderItem.setQuantity(updatedOrderItem.getQuantity()); // Update quantity
                orderItem.setPrice(updatedOrderItem.getPrice()); // Update price
                orderItem.setProduct(updatedOrderItem.getProduct()); // Update associated product
                return orderItemRepository.save(orderItem); // Save updated order item
            })
            .orElse(null); // Return null if order item is not found
    }
}

