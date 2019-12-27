package com.example.crud.service.impl;

import com.example.crud.model.Role;
import com.example.crud.model.User;
import com.example.crud.repository.RoleRepository;
import com.example.crud.repository.UserRepository;
import com.example.crud.service.RoleService;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RoleServiceImpl implements RoleService {
	@Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Role> findAllRole() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void remove(Role role) {
        roleRepository.delete(role);
    }
}