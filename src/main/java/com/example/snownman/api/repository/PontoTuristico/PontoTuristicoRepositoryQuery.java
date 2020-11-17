package com.example.snownman.api.repository.PontoTuristico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.snownman.api.dto.filters.PontoTuristicoFilter;
import com.example.snownman.api.models.PontoTuristico;

public interface PontoTuristicoRepositoryQuery {

	public Page<PontoTuristico> filtrar(PontoTuristicoFilter filter, Pageable pageable);
	
	public Page<PontoTuristico> filtrarAnonimo(PontoTuristicoFilter filter, Pageable pageable);
	
}
