package com.example.crud.service.impl;

import com.example.crud.model.Provider;
import com.example.crud.model.User;
import com.example.crud.model.UserRole;
import com.example.crud.repository.ProviderRepository;
import com.example.crud.repository.UserRepository;
import com.example.crud.service.ProviderService;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }
}