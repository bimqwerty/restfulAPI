package com.example.crud.repository;



import com.example.crud.model.Bill;
import com.example.crud.model.ImportProduct;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportProductRepository extends CrudRepository<ImportProduct, Integer> {
    @Transactional
    @Modifying
    @Query("delete ImportProduct i where  i.importId=:id")
    void deleteImportProduct(@Param("id") Integer id) ;
}