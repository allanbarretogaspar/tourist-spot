package com.example.snownman.api.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;

public abstract class AbstractRepositoryQuery<C, F> implements RepositoryQuery<C, F>{
	
	@PersistenceContext
	protected EntityManager manager;
	
	public void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
		//query.setMaxResults(200);
		
	}
	
	public Long total(F filter, Class<C> classe) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
		Root<C> root = criteria.from(classe);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		if(predicates == null) {
			predicates = new Predicate[0];
		}
		
		criteria.select(builder.count(root));
		criteria.where(predicates);
		
		return manager.createQuery(criteria).getSingleResult();
	}	
}
