package com.example.crud.service.impl;

import com.example.crud.model.PaymentMethod;
import com.example.crud.model.ShoppingCart;
import com.example.crud.repository.PaymentMethodRepository;
import com.example.crud.repository.ShoppingCartRepository;
import com.example.crud.service.PaymentMethodService;
import com.example.crud.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	@Autowired
    private ShoppingCartRepository shoppingCartRepository;


    @Override
    public List<ShoppingCart> findAllShoppingCart() {
        return (List<ShoppingCart>) shoppingCartRepository.findAll();
    }

    @Override
    public Optional<ShoppingCart> findById(Integer id) {
        return shoppingCartRepository.findById(id);
    }

    @Override
    public void save(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void remove(ShoppingCart shoppingCart) {
        shoppingCartRepository.delete(shoppingCart);
    }
}