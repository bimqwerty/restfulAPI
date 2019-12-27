package com.example.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.model.Customer;
import com.example.crud.repository.CustomerRepository;
import com.example.crud.service.CustomerService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
    private CustomerRepository CustomerRepository;

   

    @Override
    public List<Customer> findAllCustomer() {
        return (List<Customer>) CustomerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return CustomerRepository.findById(id);
    }

    @Override
    public void save(Customer Customer) {
        CustomerRepository.save(Customer);
    }

    @Override
    public void remove(Customer Customer) {
        CustomerRepository.delete(Customer);
    }
}