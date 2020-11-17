package com.example.snownman.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.snownman.api.dto.filters.FotoFilter;
import com.example.snownman.api.models.Fotos;
import com.example.snownman.api.repository.FotoRepository;

@Service
public class FotoService extends AbstractService<Fotos> {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	public Page<Fotos> filtrar(FotoFilter filter, Pageable pageable) {
		
		return fotoRepository.filtrar(filter, pageable);
		
	}

}
