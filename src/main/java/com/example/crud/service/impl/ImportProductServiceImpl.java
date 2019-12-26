package com.example.crud.service.impl;

import com.example.crud.model.ImportProduct;
import com.example.crud.model.PaymentMethod;
import com.example.crud.model.ShoppingCart;
import com.example.crud.repository.ImportProductRepository;
import com.example.crud.repository.ShoppingCartRepository;
import com.example.crud.service.ImportProductService;
import com.example.crud.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImportProductServiceImpl implements ImportProductService {
	@Autowired
    private ImportProductRepository importProductRepository;


    @Override
    public List<ImportProduct> findAllImportProduct() {
        return (List<ImportProduct>) importProductRepository.findAll();
    }

    @Override
    public Optional<ImportProduct> findById(Integer id) {
        return importProductRepository.findById(id);
    }

    @Override
    public void save(ImportProduct importProduct) {
        importProductRepository.save(importProduct);
    }

    @Override
    public void remove(ImportProduct importProduct) {
        importProductRepository.delete(importProduct);
    }
}