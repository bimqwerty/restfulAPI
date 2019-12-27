package com.example.crud.service;

import java.util.List;
import java.util.Optional;
import com.example.crud.model.Provider;

public interface ProviderService {
    List<Provider> findAllProvider();
    Optional<Provider> findById(Integer id);
    void save(Provider provider);
    void remove(Provider provider);
}