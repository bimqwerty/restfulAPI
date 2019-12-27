package com.example.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.model.Provider;
import com.example.crud.repository.ProviderRepository;
import com.example.crud.service.ProviderService;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {
	@Autowired
    private ProviderRepository ProviderRepository;

   

    @Override
    public List<Provider> findAllProvider() {
        return (List<Provider>) ProviderRepository.findAll();
    }

    @Override
    public Optional<Provider> findById(Integer id) {
        return ProviderRepository.findById(id);
    }

    @Override
    public void save(Provider provider) {
        ProviderRepository.save(provider);
    }

    @Override
    public void remove(Provider provider) {
        ProviderRepository.delete(provider);
    }
}