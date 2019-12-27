package com.example.crud.service.impl;

import com.example.crud.model.Product;
import com.example.crud.model.User;
import com.example.crud.model.UserRole;
import com.example.crud.repository.ProviderRepository;
import com.example.crud.repository.UserRoleRepository;
import com.example.crud.service.UserRoleService;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public List<UserRole> findAllUserRole() {
        return (List<UserRole>) userRoleRepository.findAll();
    }

    @Override
    public Optional<UserRole> findById(Integer id) {
        return userRoleRepository.findById(id);
    }

    @Override
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Override
    public void remove(UserRole userRole) {
        userRoleRepository.delete(userRole);
    }
}