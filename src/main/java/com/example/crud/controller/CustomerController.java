package com.example.crud.controller;


import com.example.crud.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.crud.model.Customer;
import com.example.crud.service.CustomerService;
import com.example.crud.service.impl.CustomerServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
	
	@Autowired
    private CustomerService customerService;

	@Autowired
    private CustomerRepository customerRepository ;


    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> findAllCustomer() {
        List<Customer> customers = customerService.findAllCustomer();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable("id") Integer id) {
        Optional<Customer> customer = customerService.findById(id);

        if (!customer.isPresent()) {
            return new ResponseEntity<>(customer.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/customer",
            method = RequestMethod.POST)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    	customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customer/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable("id") Integer id,
            @RequestBody Customer customer) {
        Optional<Customer> currentCustomer = customerService.findById(id);

        if (!currentCustomer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentCustomer.get().setName(customer.getName());
        currentCustomer.get().setAddress(customer.getAddress());
        currentCustomer.get().setDescription(customer.getDescription());
        currentCustomer.get().setEmail(customer.getEmail());
        currentCustomer.get().setPhoneNumber(customer.getPhoneNumber());
      currentCustomer.get().setDescription(customer.getDescription());

      customerService.save(currentCustomer.get());
        return new ResponseEntity<>(currentCustomer.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Customer> deleteCustomer(
            @PathVariable("id") Integer id) {
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.remove(customer.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @RequestMapping(value = "/customer/search", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<List<Customer>> getListCustomers(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "email", required = false, defaultValue = "") String email,
            @RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber) {
        List<Customer> customers = customerRepository.findByCustomer(name,email,phoneNumber);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    
    


}

