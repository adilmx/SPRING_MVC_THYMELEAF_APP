package com.adilmx.spring_mvc_app.service.impl;

import com.adilmx.spring_mvc_app.entities.Contrat;
import com.adilmx.spring_mvc_app.repository.ContratRepo;
import com.adilmx.spring_mvc_app.service.ContratService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContratServiceImpl implements ContratService {
    private ContratRepo contratRepo;

    public ContratServiceImpl(ContratRepo contratRepo) {
        this.contratRepo = contratRepo;
    }

    @Override
    public List<Contrat> findAll() {
        return contratRepo.findAll();
    }

    @Override
    public Page<Contrat> findByTitleContains(String keyWord, int page, int size) {
        return contratRepo.findByTitleContains(keyWord, PageRequest.of(page, size));
    }

    @Override
    public int deleteById(Long id) {
        contratRepo.deleteById(id);
        return 1;
    }

    @Override
    public Contrat save(Contrat contrat) {
        return contratRepo.save(contrat);
    }

    @Override
    public Optional<Contrat> findById(Long id) {
        return contratRepo.findById(id);
    }
}
