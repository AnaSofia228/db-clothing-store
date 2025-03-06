package com.example.clothing_store.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clothing_store.models.Order;
import com.example.clothing_store.repository.OrderRepository;

import jakarta.persistence.EntityNotFoundException;

// Service class to handle related operations
@Service
public class OrderService {
    // Add OrderRepository to interact with the database
    @Autowired
    private OrderRepository orderRepository;

    // Save a new order to the database
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    // Display all orders from the database
    public List<Order> findAll() {
        List<Order> orderToReturn = new ArrayList<>();
        orderToReturn = orderRepository.findAll();
        return orderToReturn;
    }

    // Delete an order by its ID
    public void deleteOrderById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found"); // Throw exception if order doesn't exist
        }
        orderRepository.deleteById(id);
    }

    // Update an existing order by its ID
    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id)
            .map(order -> {
                order.setOrderDate(updatedOrder.getOrderDate()); // Update order date
                order.setTotalAmount(updatedOrder.getTotalAmount()); // Update total amount
                order.setCustomer(updatedOrder.getCustomer()); // Update associated customer
                return orderRepository.save(order); // Save updated order
            })
            .orElse(null); // Return null if order is not found
    }
}

