package com.example.crud.service;

import java.util.List;
import java.util.Optional;

import com.example.crud.model.Customer;
import com.example.crud.model.Product;

public interface CustomerService {
    List<Customer> findAllCustomer();
    Optional<Customer> findById(Integer id);
    void save(Customer customer);
    void remove(Customer customer);
}