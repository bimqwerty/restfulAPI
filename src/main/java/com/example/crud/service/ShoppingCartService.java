package com.example.crud.service;

import com.example.crud.model.PaymentMethod;
import com.example.crud.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ShoppingCartService {
    List<ShoppingCart> findAllShoppingCart();
    Optional<ShoppingCart> findById(Integer id);
    void save(ShoppingCart shoppingCart);
    void remove(ShoppingCart shoppingCart);
}