package com.example.crud.service;

import com.example.crud.model.ImportProduct;
import com.example.crud.model.PaymentMethod;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PaymentMethodService {
    List<PaymentMethod> findAllPaymentMethod();
    Optional<PaymentMethod> findById(Integer id);
    void save(PaymentMethod paymentMethod);
    void remove(PaymentMethod paymentMethod);
}