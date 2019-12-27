package com.example.crud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.crud.model.Provider;
import com.example.crud.service.ProviderService;

import java.util.List;
import java.util.Optional;

@RestController
public class ProviderController {
	
	@Autowired
    private ProviderService providerService;


    @RequestMapping(value = "/providers", method = RequestMethod.GET)
    public ResponseEntity<List<Provider>> findAllProvider() {
        List<Provider> providers = providerService.findAllProvider();
        if (providers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

    @RequestMapping(value = "/provider/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Provider> getProviderById(
            @PathVariable("id") Integer id) {
        Optional<Provider> provider = providerService.findById(id);

        if (!provider.isPresent()) {
            return new ResponseEntity<>(provider.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(provider.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/provider",
            method = RequestMethod.POST)
    public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) {
    	providerService.save(provider);
        return new ResponseEntity<>(provider, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/provider/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Provider> updateProvider(
            @PathVariable("id") Integer id,
            @RequestBody Provider provider) {
        Optional<Provider> currentProvider = providerService.findById(id);

        if (!currentProvider.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentProvider.get().setName(provider.getName());
        currentProvider.get().setAddress(provider.getAddress());
        currentProvider.get().setDescription(provider.getDescription());
        currentProvider.get().setTaxCode(provider.getTaxCode());
        currentProvider.get().setEmail(provider.getEmail());
        currentProvider.get().setPhoneNumber(provider.getPhoneNumber());
      currentProvider.get().setDescription(provider.getDescription());

      providerService.save(currentProvider.get());
        return new ResponseEntity<>(currentProvider.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/provider/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Provider> deleteProvider(
            @PathVariable("id") Integer id) {
        Optional<Provider> provider = providerService.findById(id);
        if (!provider.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        providerService.remove(provider.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

