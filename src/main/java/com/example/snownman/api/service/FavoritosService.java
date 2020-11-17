package com.example.snownman.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.snownman.api.dto.filters.FavoritosFilter;
import com.example.snownman.api.models.Favoritos;
import com.example.snownman.api.repository.FavoritosRepository;

@Service
public class FavoritosService extends AbstractService<Favoritos> {
	
	@Autowired
	private FavoritosRepository favoritosRepository;
	
	public Page<Favoritos> filtrar(FavoritosFilter filter, Pageable pageable) {
		
		return favoritosRepository.filtrar(filter, pageable);
		
	}

}
