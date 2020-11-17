package com.example.snownman.api.repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepositoryQuery<C, F> {
	abstract Page<C> filtrar(F filter, Pageable pageable);
	
	abstract void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) ;
	
	abstract Long total(F filter, Class<C> classe);
	
	abstract Predicate[] criarRestricoes(F filter, CriteriaBuilder builder, Root<C> root) ;
}
