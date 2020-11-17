package com.example.snownman.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.snownman.api.models.Fotos;
import com.example.snownman.api.repository.fotos.FotoRepositoryQuery;

@Repository
public interface FotoRepository extends JpaRepository<Fotos, Long>, FotoRepositoryQuery {
	
	

}
