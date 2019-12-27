package com.example.crud.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.model.Product;
import com.example.crud.model.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Integer> {
}