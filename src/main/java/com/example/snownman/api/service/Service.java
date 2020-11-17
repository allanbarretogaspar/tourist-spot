package com.example.snownman.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface Service<C> {
	
	public List<C> listar();

	public C carregar(Long id);
	
	public ResponseEntity<C> buscar(Long id);

	public C criar(C entidade);
	
	public C atualizar(Long id, C entidade);

	public void apagar(Long id);

}
