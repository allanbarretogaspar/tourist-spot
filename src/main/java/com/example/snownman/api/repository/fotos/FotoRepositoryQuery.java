package com.example.snownman.api.repository.fotos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.snownman.api.dto.filters.FotoFilter;
import com.example.snownman.api.models.Fotos;

public interface FotoRepositoryQuery {

	public Page<Fotos> filtrar(FotoFilter filter, Pageable pageable);
}
