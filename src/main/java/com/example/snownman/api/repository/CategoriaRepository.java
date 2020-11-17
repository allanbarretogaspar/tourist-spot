package com.example.snownman.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.snownman.api.models.Categoria;
import com.example.snownman.api.repository.categoria.CategoriaRepositoryQuery;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>, CategoriaRepositoryQuery {
	
	

}
