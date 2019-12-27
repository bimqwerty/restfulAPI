package com.example.crud.service;

import com.example.crud.model.User;
import com.example.crud.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserRoleService {
    List<UserRole> findAllUserRole();
    Optional<UserRole> findById(Integer id);
    void save(UserRole userRole);
    void remove(UserRole userRole);
}