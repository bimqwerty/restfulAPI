package com.example.crud.repository;



import com.example.crud.model.Bill;
import com.example.crud.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends CrudRepository<Bill, Integer> {

}