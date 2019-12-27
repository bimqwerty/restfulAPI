package com.example.crud.repository;



import com.example.crud.model.Bill;
import com.example.crud.model.Provider;
import com.example.crud.model.UserRole;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends CrudRepository<Bill, Integer> {
	@Query(value = "SELECT MAX(id) FROM bill", nativeQuery = true)
    Integer getMaxId();
	
    @Query(" from Bill b,Customer c where :customerName is null or (b.customerId=c.id and c.name like %:customerName%) ")
    List<Bill> findByBill(@Param("customerName") String customerName);

	
}