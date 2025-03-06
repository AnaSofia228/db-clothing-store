package com.example.clothing_store.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clothing_store.models.Customer;
import com.example.clothing_store.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;

// Service class to handle related operations
@Service
public class CustomerService {
    // Add CustomerRepository to interact with the database
    @Autowired
    private CustomerRepository customerRepository;

    // Save a new customer to the database
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Display all customers from the database
    public List<Customer> findAll() {
        List<Customer> customerToReturn = new ArrayList<>();
        customerToReturn = customerRepository.findAll();
        return customerToReturn;
    }

    // Delete a customer by its ID
    public void deleteCustomerById(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found"); // Throw exception if customer doesn't exist
        }
        customerRepository.deleteById(id);
    }

    // Update an existing customer by its ID
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id)
            .map(customer -> {
                customer.setName(updatedCustomer.getName()); // Update customer name
                customer.setLastname(updatedCustomer.getLastname()); // Update customer last name
                customer.setEmail(updatedCustomer.getEmail()); // Update customer email
                customer.setPhone(updatedCustomer.getPhone()); // Update customer phone
                customer.setOrders(updatedCustomer.getOrders()); // Update customer's orders
                return customerRepository.save(customer); // Save updated customer
            })
            .orElse(null); // Return null if customer is not found
    }
}
