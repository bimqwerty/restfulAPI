package com.example.crud.service;

import com.example.crud.model.Bill;
import com.example.crud.model.ImportProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ImportProductService {
    List<ImportProduct> findAllImportProduct();
    Optional<ImportProduct> findById(Integer id);
    void save(ImportProduct importProduct);
    void remove(ImportProduct importProduct);
}