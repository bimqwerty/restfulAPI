package com.example.crud.repository;



import com.example.crud.model.ImportProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportProductRepository extends CrudRepository<ImportProduct, Integer> {

}