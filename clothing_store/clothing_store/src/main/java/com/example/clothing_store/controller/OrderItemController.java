package com.example.clothing_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.clothing_store.models.OrderItem;
import com.example.clothing_store.services.OrderItemService;

import jakarta.persistence.EntityNotFoundException;

// The REST class controller handles HTTP requests
@RestController
// Assign requests to "/order_item"
@RequestMapping("/order_item")
public class OrderItemController {
    // Automatically add an OrderItemService instance
    @Autowired
    private OrderItemService orderItemService;

    // HTTP POST to create a new order item
    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.saveOrderItem(orderItem);
    }

    // HTTP GET request to retrieve all order items
    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.findAll();
    }

    // HTTP DELETE request to delete an order item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable Long id) {
        try {
            // Attempt to delete the order item by ID, calling the service layer method
            orderItemService.deleteOrderItemById(id);
            return ResponseEntity.ok("Order item with id "+ id + " successfully removed");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order item not found");
        }
    }

    // HTTP PUT request to update an existing order item by ID
    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem updatedOrderItem) {
        OrderItem orderItem = orderItemService.updateOrderItem(id, updatedOrderItem);
        return orderItem != null ? ResponseEntity.ok(orderItem) : ResponseEntity.notFound().build(); // If the order item does not exist, it returns status 404
    }
}
