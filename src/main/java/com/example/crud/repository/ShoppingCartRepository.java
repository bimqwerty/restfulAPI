package com.example.crud.repository;



import com.example.crud.model.ShoppingCart;
import com.example.crud.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {
	   @Query("from ShoppingCart s where s.billId=:billId")
	    List<ShoppingCart> findShoppingCart(@Param("billId") Integer billId) ;
}