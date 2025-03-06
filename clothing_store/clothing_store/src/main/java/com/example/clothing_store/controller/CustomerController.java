package com.example.clothing_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.clothing_store.models.Customer;
import com.example.clothing_store.services.CustomerService;

import jakarta.persistence.EntityNotFoundException;

// The REST class controller handles HTTP requests
@RestController
// Assign requests to "/customer"
@RequestMapping("/customer")
public class CustomerController {
    // Automatically add a instance
    @Autowired 
    private CustomerService customerService;

    // HTTP POST to create a new customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    // HTTP GET request to retrieve all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    // HTTP DELETE request to delete a customer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        try {
            // Try to delete the customer by ID, calling the service layer method
            customerService.deleteCustomerById(id); // Call and look for the service to remove the customer
            return ResponseEntity.ok("Customer with id " + id + " successfully removed");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }

    // HTTP PUT request to update an existing customer by ID
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.updateCustomer(id, updatedCustomer);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build(); // If the customer does not exist, it returns status 404
    }
}
