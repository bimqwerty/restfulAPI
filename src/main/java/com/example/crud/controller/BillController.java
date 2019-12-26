package com.example.crud.controller;


import com.example.crud.model.Bill;
import com.example.crud.model.UserRole;
import com.example.crud.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BillController {
	
	@Autowired
    private BillService billService;


    @RequestMapping(value = "/bills", method = RequestMethod.GET)
    public ResponseEntity<List<Bill>> findAllProvider() {
        List<Bill> bills = billService.findAllBill();
        if (bills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @RequestMapping(value = "/bill/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bill> getProviderById(
            @PathVariable("id") Integer id) {
        Optional<Bill> bill = billService.findById(id);

        if (!bill.isPresent()) {
            return new ResponseEntity<>(bill.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bill.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/bill",
            method = RequestMethod.POST)
    public ResponseEntity<Bill> createProvider(@RequestBody Bill bill) {
        billService.save(bill);
        return new ResponseEntity<>(bill, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/bill/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Bill> updateProvider(
            @PathVariable("id") Integer id,
            @RequestBody Bill bill) {
        Optional<Bill> currentBill  = billService.findById(id);

        if (!currentBill.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentBill.get().setTaxCode(bill.getTaxCode());
        currentBill.get().setCreatedDate(bill.getCreatedDate());
        currentBill.get().setCustomerId(bill.getCustomerId());
        currentBill.get().setPaymentMethodId(bill.getPaymentMethodId());
        currentBill.get().setTotalPrice(bill.getTotalPrice());
        currentBill.get().setUpdatedDate(bill.getUpdatedDate());
        currentBill.get().setUserId(bill.getUserId());




      billService.save(currentBill.get());
        return new ResponseEntity<>(currentBill.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/bill/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<UserRole> deleteProvider(
            @PathVariable("id") Integer id) {
        Optional<Bill> bill = billService.findById(id);
        if (!bill.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        billService.remove(bill.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

