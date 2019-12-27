package com.example.crud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.crud.model.Bill;
import com.example.crud.model.Import;
import com.example.crud.model.ImportProduct;
import com.example.crud.repository.ImportProductRepository;
import com.example.crud.repository.ImportRepository;
import com.example.crud.request.ImportRequest;
import com.example.crud.service.ImportProductService;
import com.example.crud.service.ImportService;
import com.example.crud.service.impl.ImportServiceImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class ImportController {
	
	@Autowired
    private ImportService importsService;

	@Autowired
	ImportRepository importRepository;
	
	@Autowired
	ImportProductService importProductService;
	
	@Autowired
	ImportProductRepository importProductRepository;

    @RequestMapping(value = "/imports/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Import>> findAllImport() {
        List<Import> importss = importsService.findAllImport();
        if (importss.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(importss, HttpStatus.OK);
    }

    @RequestMapping(value = "/imports/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Import> getImportById(
            @PathVariable("id") Integer id) {
        Optional<Import> imports = importsService.findById(id);

        if (!imports.isPresent()) {
            return new ResponseEntity<>(imports.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(imports.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/imports",
            method = RequestMethod.POST)
    public ResponseEntity<Import> createImport(@RequestBody ImportRequest importRequest) {
    	Import imports = new Import();
    	imports.setCreatedDate(new Date());
    	imports.setUpdatedDate(new Date());
    	imports.setDescription(importRequest.getDescription());
    	imports.setUserId(importRequest.getUserId());
    	imports.setProviderId(importRequest.getProviderId());
    	imports.setPaymentMethodId(importRequest.getPaymentMethodId());
    	imports.setInvoiceCode(importRequest.getInvoiceCode());
    	importsService.save(imports);
    	
    	List<Integer> productList = importRequest.getProductIdList();
    	Integer idImport= importRepository.getMaxId();
    	
        Iterator<Integer> iter = productList.iterator();
        while (iter.hasNext()) {
        	ImportProduct importProduct = new ImportProduct();
        	importProduct.setImportId(idImport);
        	importProduct.setProductId(iter.next());
        	importProductService.save(importProduct);
        }
    	
        return new ResponseEntity<>(imports, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/imports/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Import> updateImport(
            @PathVariable("id") Integer id,
            @RequestBody ImportRequest importRequest) {
        Optional<Import> importOpt = importsService.findById(id);
        Import imports = importOpt.get();
    	
    	
    	imports.setUpdatedDate(new Date());
    	imports.setDescription(importRequest.getDescription());
    	imports.setUserId(importRequest.getUserId());
    	imports.setProviderId(importRequest.getProviderId());
    	imports.setPaymentMethodId(importRequest.getPaymentMethodId());
    	imports.setInvoiceCode(importRequest.getInvoiceCode());
    	importsService.save(imports);
    	
    	importProductRepository.deleteImportProduct(id);
    	
    	List<Integer> productList = importRequest.getProductIdList();   	
    	
        Iterator<Integer> iter = productList.iterator();
        while (iter.hasNext()) {
        	ImportProduct importProduct = new ImportProduct();
        	importProduct.setImportId(id);
        	importProduct.setProductId(iter.next());
        	importProductService.save(importProduct);
        }
    	
        return new ResponseEntity<>(imports, HttpStatus.CREATED);
    }
    

    @RequestMapping(value = "/imports/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Import> deleteImport(
            @PathVariable("id") Integer id) {
        Optional<Import> imports = importsService.findById(id);
        if (!imports.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        importsService.remove(imports.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


