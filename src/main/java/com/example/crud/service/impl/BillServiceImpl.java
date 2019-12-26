package com.example.crud.service.impl;

import com.example.crud.model.Bill;
import com.example.crud.model.Import;
import com.example.crud.repository.BillRepository;
import com.example.crud.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {
	@Autowired
    private BillRepository billRepository;


    @Override
    public List<Bill> findAllBill() {
        return (List<Bill>) billRepository.findAll();
    }

    @Override
    public Optional<Bill> findById(Integer id) {
        return billRepository.findById(id);
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public void remove(Bill bill) {
        billRepository.delete(bill);
    }
}