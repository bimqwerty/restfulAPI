package com.example.crud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.crud.model.Import;
import com.example.crud.service.ImportService;
import com.example.crud.service.impl.ImportServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
public class ImportController {
	
	@Autowired
    private ImportService importsService;


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
    public ResponseEntity<Import> createImport(@RequestBody Import imports) {
    	importsService.save(imports);
        return new ResponseEntity<>(imports, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/imports/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Import> updateImport(
            @PathVariable("id") Integer id,
            @RequestBody Import imports) {
        Optional<Import> currentImport = importsService.findById(id);

        if (!currentImport.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

       
        currentImport.get().setProviderId(imports.getProviderId());
        currentImport.get().setInvoiceCode(imports.getInvoiceCode());
        currentImport.get().setCreatedDate(imports.getCreatedDate());
        currentImport.get().setUpdatedDate(imports.getUpdatedDate());
        currentImport.get().setDescription(imports.getDescription());
        currentImport.get().setPaymentMethodId(imports.getPaymentMethodId());
        currentImport.get().setUserId(imports.getUserId());


      currentImport.get().setDescription(imports.getDescription());

      importsService.save(currentImport.get());
        return new ResponseEntity<>(currentImport.get(), HttpStatus.OK);
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
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


