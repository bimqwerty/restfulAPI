package com.example.crud.service;

import com.example.crud.model.Provider;
import com.example.crud.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> findAllUser();
    Optional<User> findById(Integer id);
    void save(User user);
    void remove(User user);
}