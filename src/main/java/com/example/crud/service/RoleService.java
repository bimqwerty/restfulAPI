package com.example.crud.service;

import com.example.crud.model.Role;
import com.example.crud.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    List<Role> findAllRole();
    Optional<Role> findById(Integer id);
    void save(Role role);
    void remove(Role role);
}