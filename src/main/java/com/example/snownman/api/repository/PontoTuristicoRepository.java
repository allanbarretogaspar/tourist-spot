package com.example.snownman.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.snownman.api.models.PontoTuristico;
import com.example.snownman.api.repository.PontoTuristico.PontoTuristicoRepositoryQuery;

@Repository
public interface PontoTuristicoRepository extends JpaRepository<PontoTuristico, Long>, PontoTuristicoRepositoryQuery {
	
	

}
