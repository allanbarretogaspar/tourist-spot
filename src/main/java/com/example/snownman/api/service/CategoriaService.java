package com.example.snownman.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.snownman.api.dto.filters.CategoriaFilter;
import com.example.snownman.api.models.Categoria;
import com.example.snownman.api.repository.CategoriaRepository;

@Service
public class CategoriaService extends AbstractService<Categoria> {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Page<Categoria> filtrar(CategoriaFilter filter, Pageable pageable) {
		
		return categoriaRepository.filtrar(filter, pageable);
		
	}
	
	public List<Categoria>categoriasIniciasUserAnonimo(List<String> ids){
		
		return this.categoriaRepository.categoriasIniciasUserAnonimo(ids);
	}

}
