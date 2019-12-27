package com.example.crud.repository;



import com.example.crud.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("from User u where u.username=:userName")
    Optional<User> findByUsername(@Param("userName") String userName) ;
}