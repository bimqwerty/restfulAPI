package com.example.crud.controller;


import com.example.crud.model.PaymentMethod;
import com.example.crud.model.ShoppingCart;
import com.example.crud.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ShoppingCartController {
	
	@Autowired
    private ShoppingCartService shoppingCartService;


    @RequestMapping(value = "/shoppingCart", method = RequestMethod.GET)
    public ResponseEntity<List<ShoppingCart>> findAllProvider() {
        List<ShoppingCart> shoppingCarts = shoppingCartService.findAllShoppingCart();
        if (shoppingCarts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    @RequestMapping(value = "/shoppingCart/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShoppingCart> getProviderById(
            @PathVariable("id") Integer id) {
        Optional<ShoppingCart> shoppingCart = shoppingCartService.findById(id);

        if (!shoppingCart.isPresent()) {
            return new ResponseEntity<>(shoppingCart.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shoppingCart.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/shoppingCart",
            method = RequestMethod.POST)
    public ResponseEntity<ShoppingCart> createProvider(@RequestBody ShoppingCart shoppingCart) {
        shoppingCartService.save(shoppingCart);
        return new ResponseEntity<>(shoppingCart, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/shoppingCart/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<ShoppingCart> updateProvider(
            @PathVariable("id") Integer id,
            @RequestBody ShoppingCart shoppingCart) {
        Optional<ShoppingCart> shoppingCart1  = shoppingCartService.findById(id);

        if (!shoppingCart1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        shoppingCart1.get().setAmount(shoppingCart.getAmount());
        shoppingCart1.get().setBillId(shoppingCart.getBillId());
        shoppingCart1.get().setProductId(shoppingCart.getProductId());
        shoppingCart1.get().setTotalPrice(shoppingCart.getTotalPrice());

        shoppingCartService.save(shoppingCart1.get());
        return new ResponseEntity<>(shoppingCart1.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/shoppingCart/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<ShoppingCart> deleteProvider(
            @PathVariable("id") Integer id) {
        Optional<ShoppingCart> shoppingCart = shoppingCartService.findById(id);
        if (!shoppingCart.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        shoppingCartService.remove(shoppingCart.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

