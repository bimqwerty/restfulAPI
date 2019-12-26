package com.example.crud.repository;



import com.example.crud.model.PaymentMethod;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Integer> {

}