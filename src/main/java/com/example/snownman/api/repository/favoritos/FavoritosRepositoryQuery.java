package com.example.snownman.api.repository.favoritos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.snownman.api.dto.filters.FavoritosFilter;
import com.example.snownman.api.models.Favoritos;

public interface FavoritosRepositoryQuery {

	public Page<Favoritos> filtrar(FavoritosFilter filter, Pageable pageable);
}
