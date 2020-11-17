package com.example.snownman.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;


public abstract class AbstractService<C>  implements Service<C>{
	
	@Autowired
	private JpaRepository<C, Long> repository;
	
	public List<C> listar() {
		List<C> entidades = (List<C>) repository.findAll();
		return entidades;
	}

	public C carregar(Long id) {
		return repository.getOne(id);
	}

	
	public ResponseEntity<C> buscar(Long id) {

		C entidade = repository.getOne(id);

		return entidade == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(entidade);
	}

	public C criar(C entidade) {
		C entidadeSalva = repository.save(entidade);
		return entidadeSalva;
	}
	
	
	
	public C atualizar(Long id, C entidade) {
		
		C entidadeSalva = repository.getOne(id);

		if (entidadeSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		

		return repository.save(entidade);
	}

	@Override
	public void apagar(Long id) {
		
		repository.deleteById(id);
	}
	
	

}
