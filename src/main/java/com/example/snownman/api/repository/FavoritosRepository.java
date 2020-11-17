package com.example.snownman.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.snownman.api.models.Favoritos;
import com.example.snownman.api.repository.favoritos.FavoritosRepositoryQuery;

@Repository
public interface FavoritosRepository extends JpaRepository<Favoritos, Long>, FavoritosRepositoryQuery {
		

}
