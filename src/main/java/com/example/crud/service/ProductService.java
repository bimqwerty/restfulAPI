package com.example.crud.service;

import java.util.List;
import java.util.Optional;
import com.example.crud.model.Product;

public interface ProductService {
    List<Product> findAllProduct();
    Optional<Product> findById(Integer id);
    void save(Product product);
    void remove(Product product);
}