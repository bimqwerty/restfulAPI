package com.example.crud.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.crud.model.Provider;

import java.util.List;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Integer> {

    @Query(" from Provider a where :name is null or a.name like %:name% ")
    List<Provider> findByProvider(@Param("name") String name);

}