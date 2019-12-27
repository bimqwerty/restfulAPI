package com.example.crud.repository;


import com.example.crud.model.Provider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.crud.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(" from Product a where :name is null or a.name like %:name% ")
    List<Product> findByProduct(@Param("name") String name);
}