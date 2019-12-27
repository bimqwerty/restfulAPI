package com.example.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.model.Import;
import com.example.crud.repository.ImportRepository;
import com.example.crud.service.ImportService;

import java.util.List;
import java.util.Optional;

@Service
public class ImportServiceImpl implements ImportService {
	@Autowired
    private ImportRepository ImportRepository;

   

    @Override
    public List<Import> findAllImport() {
        return (List<Import>) ImportRepository.findAll();
    }

    @Override
    public Optional<Import> findById(Integer id) {
        return ImportRepository.findById(id);
    }

    @Override
    public void save(Import imports) {
        ImportRepository.save(imports);
    }

    @Override
    public void remove(Import imports) {
        ImportRepository.delete(imports);
    }
}