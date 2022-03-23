package com.adilmx.spring_mvc_app.service;

import com.adilmx.spring_mvc_app.entities.Contrat;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ContratService {
    List<Contrat> findAll();
    Page<Contrat> findByTitleContains(String keyWord, int page , int size);
    int deleteById(Long id);
    Contrat save(Contrat contrat);
    Optional<Contrat> findById(Long id);
}
