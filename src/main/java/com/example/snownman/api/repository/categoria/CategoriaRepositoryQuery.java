package com.example.snownman.api.repository.categoria;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.snownman.api.dto.filters.CategoriaFilter;
import com.example.snownman.api.models.Categoria;

public interface CategoriaRepositoryQuery {

	public Page<Categoria> filtrar(CategoriaFilter filter, Pageable pageable);
	public List<Categoria> categoriasIniciasUserAnonimo(List<String> descricoes);
}
