package com.example.snownman.api.repository.fotos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.snownman.api.dto.filters.FotoFilter;
import com.example.snownman.api.models.Fotos;
import com.example.snownman.api.models.Fotos_;
import com.example.snownman.api.repository.AbstractRepositoryQuery;


public class FotoRepositoryQueryImpl  extends AbstractRepositoryQuery<Fotos, FotoFilter> implements FotoRepositoryQuery {

	@Override
	public Page<Fotos> filtrar(FotoFilter filter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Fotos> criteria = builder.createQuery(Fotos.class);
		Root<Fotos> root = criteria.from(Fotos.class);
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		TypedQuery<Fotos> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter, Fotos.class));
	}
	
	@Override
	public Predicate[] criarRestricoes(FotoFilter filter, CriteriaBuilder builder, Root<Fotos> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(filter.getId() != null) {
			
			predicates.add(builder.equal(root.get(Fotos_.id), filter.getId()));
		}
		
		if(filter.getDescricao() != null && !filter.getDescricao().isEmpty()) {
			
			predicates.add(
					builder.like(
							builder.lower(root.get(Fotos_.descricao)), 
							"%" + filter.getDescricao().toLowerCase() + "%")
					);
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
