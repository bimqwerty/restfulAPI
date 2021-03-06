package com.example.crud.repository;


import com.example.crud.model.Role;
import com.example.crud.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    @Query("select r.rolename from Role r where r.id=:roleId")
    String getRoleName(Integer roleId) ;
}