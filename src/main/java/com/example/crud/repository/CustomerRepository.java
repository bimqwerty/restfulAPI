package com.example.crud.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.model.Customer;
import com.example.crud.model.Product;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}