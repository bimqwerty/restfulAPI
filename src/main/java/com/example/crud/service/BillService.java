package com.example.crud.service;

import com.example.crud.model.Bill;
import com.example.crud.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BillService {
    List<Bill> findAllBill();
    Optional<Bill> findById(Integer id);
    void save(Bill bill);
    void remove(Bill bill);
}