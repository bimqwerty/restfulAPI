package com.example.crud.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.crud.model.Customer;
import com.example.crud.model.Product;
import com.example.crud.model.Provider;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @Query(" from Customer a where (:name is null or a.name like %:name%) and (:email is null or a.email like %:email%) and (:phoneNumber is null or a.phoneNumber like %:phoneNumber%) ")
    List<Customer> findByCustomer(@Param("name") String name , @Param("email") String email , @Param("phoneNumber") String phoneNumber);
       

}