package com.example.crud.controller;


import com.example.crud.model.Bill;
import com.example.crud.model.ImportProduct;
import com.example.crud.model.UserRole;
import com.example.crud.service.BillService;
import com.example.crud.service.ImportProductService;
import com.example.crud.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ImportProductController {
	
	@Autowired
    private ImportProductService importProductService;


    @RequestMapping(value = "/importProducts", method = RequestMethod.GET)
    public ResponseEntity<List<ImportProduct>> findAllProvider() {
        List<ImportProduct> importProducts = importProductService.findAllImportProduct();
        if (importProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(importProducts, HttpStatus.OK);
    }

    @RequestMapping(value = "/importProduct/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImportProduct> getProviderById(
            @PathVariable("id") Integer id) {
        Optional<ImportProduct> importProduct = importProductService.findById(id);

        if (!importProduct.isPresent()) {
            return new ResponseEntity<>(importProduct.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(importProduct.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/importProduct",
            method = RequestMethod.POST)
    public ResponseEntity<ImportProduct> createProvider(@RequestBody ImportProduct importProduct) {
        importProductService.save(importProduct);
        return new ResponseEntity<>(importProduct, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/importProduct/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<ImportProduct> updateProvider(
            @PathVariable("id") Integer id,
            @RequestBody ImportProduct importProduct1) {
        Optional<ImportProduct> importProduct  = importProductService.findById(id);

        if (!importProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        importProduct.get().setImportId(importProduct1.getImportId());
        importProduct.get().setProductId(importProduct1.getProductId());

        importProductService.save(importProduct.get());
        return new ResponseEntity<>(importProduct.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/bill/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<ImportProduct> deleteProvider(
            @PathVariable("id") Integer id) {
        Optional<ImportProduct> importProduct = importProductService.findById(id);
        if (!importProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        importProductService.remove(importProduct.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

