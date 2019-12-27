package com.example.crud.service;

import java.util.List;
import java.util.Optional;
import com.example.crud.model.Import;

public interface ImportService {
    List<Import> findAllImport();
    Optional<Import> findById(Integer id);
    void save(Import imports);
    void remove(Import imports);
}