package com.example.crud.controller;


import com.example.crud.model.ImportProduct;
import com.example.crud.model.PaymentMethod;
import com.example.crud.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PaymentMethodController {
	
	@Autowired
    private PaymentMethodService paymentMethodService;


    @RequestMapping(value = "/paymentMethods", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentMethod>> findAllProvider() {
        List<PaymentMethod> paymentMethods = paymentMethodService.findAllPaymentMethod();
        if (paymentMethods.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(paymentMethods, HttpStatus.OK);
    }

    @RequestMapping(value = "/paymentMethod/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentMethod> getProviderById(
            @PathVariable("id") Integer id) {
        Optional<PaymentMethod> paymentMethod = paymentMethodService.findById(id);

        if (!paymentMethod.isPresent()) {
            return new ResponseEntity<>(paymentMethod.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(paymentMethod.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/paymentMethod",
            method = RequestMethod.POST)
    public ResponseEntity<PaymentMethod> createProvider(@RequestBody PaymentMethod paymentMethod) {
        paymentMethodService.save(paymentMethod);
        return new ResponseEntity<>(paymentMethod, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/paymentMethod/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<PaymentMethod> updateProvider(
            @PathVariable("id") Integer id,
            @RequestBody PaymentMethod paymentMethod1) {
        Optional<PaymentMethod> paymentMethod  = paymentMethodService.findById(id);

        if (!paymentMethod.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        paymentMethod.get().setDescription(paymentMethod1.getDescription());
        paymentMethod.get().setName(paymentMethod1.getName());

        paymentMethodService.save(paymentMethod.get());
        return new ResponseEntity<>(paymentMethod.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/paymentMethod/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<PaymentMethod> deleteProvider(
            @PathVariable("id") Integer id) {
        Optional<PaymentMethod> paymentMethod = paymentMethodService.findById(id);
        if (!paymentMethod.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paymentMethodService.remove(paymentMethod.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

