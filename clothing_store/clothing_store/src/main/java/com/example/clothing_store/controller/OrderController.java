package com.example.clothing_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.clothing_store.models.Order;
import com.example.clothing_store.services.OrderService;

import jakarta.persistence.EntityNotFoundException;

// The REST class controller handles HTTP requests
@RestController
// Assign requests to "/order"
@RequestMapping("/order")
public class OrderController {
    // Automatically add an OrderService instance
    @Autowired
    private OrderService orderService;

    // HTTP POST to create a new order
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    // HTTP GET request to retrieve all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    // HTTP DELETE request to delete an order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        try {
            // Attempt to delete the order by ID, calling the service layer method
            orderService.deleteOrderById(id);
            return ResponseEntity.ok("Order with id " + id + " successfully removed");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }

    // HTTP PUT request to update an existing order by ID
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        Order order = orderService.updateOrder(id, updatedOrder);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build(); // If the order does not exist, it returns status 404
    }
}
