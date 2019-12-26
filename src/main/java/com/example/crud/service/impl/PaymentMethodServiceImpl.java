package com.example.crud.service.impl;

import com.example.crud.model.Bill;
import com.example.crud.model.Import;
import com.example.crud.model.PaymentMethod;
import com.example.crud.repository.BillRepository;
import com.example.crud.repository.PaymentMethodRepository;
import com.example.crud.service.BillService;
import com.example.crud.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
	@Autowired
    private PaymentMethodRepository paymentMethodRepository;


    @Override
    public List<PaymentMethod> findAllPaymentMethod() {
        return (List<PaymentMethod>) paymentMethodRepository.findAll();
    }

    @Override
    public Optional<PaymentMethod> findById(Integer id) {
        return paymentMethodRepository.findById(id);
    }

    @Override
    public void save(PaymentMethod paymentMethod) {
        paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public void remove(PaymentMethod paymentMethod) {
        paymentMethodRepository.delete(paymentMethod);
    }
}