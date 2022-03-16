package com.adilmx.spring_mvc_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adilmx.spring_mvc_app.entities.Contrat;

@Repository
public interface ContratRepo extends JpaRepository<Contrat, Long> {
	public Page<Contrat> findByTitleContains(String keyWord, Pageable pageable);
}
