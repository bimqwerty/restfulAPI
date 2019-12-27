package com.example.crud.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.model.Import;
import com.example.crud.model.Product;

@Repository
public interface ImportRepository extends CrudRepository<Import, Integer> {
}