package com.example.crud.repository;



import com.example.crud.model.Role;
import com.example.crud.model.UserRole;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    @Query(" select u.roleId from UserRole u where u.userId=:userId")
    List<Integer> findByUserId(Integer userId) ;
    
    
    @Transactional
    @Modifying
    @Query("delete UserRole u where (u.userId = :userId  and u.roleId=:roleId)")
    void deleteUR(@Param("userId") Integer userId,@Param("roleId") Integer roleId) ;
}