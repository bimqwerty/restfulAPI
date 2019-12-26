package com.example.crud.repository;



import com.example.crud.model.Role;
import com.example.crud.model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    @Query("from UserRole u where u.user_id=:userId")
    List<UserRole> findByUserId(Integer userId) ;
}