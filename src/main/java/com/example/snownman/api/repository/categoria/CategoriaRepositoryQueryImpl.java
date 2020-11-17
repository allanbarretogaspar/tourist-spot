package com.example.snownman.api.repository.categoria;

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

import com.example.snownman.api.dto.filters.CategoriaFilter;
import com.example.snownman.api.models.Categoria;
import com.example.snownman.api.models.Categoria_;
import com.example.snownman.api.repository.AbstractRepositoryQuery;


public class CategoriaRepositoryQueryImpl  extends AbstractRepositoryQuery<Categoria, CategoriaFilter> implements CategoriaRepositoryQuery {

	@Override
	public Page<Categoria> filtrar(CategoriaFilter filter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Categoria> criteria = builder.createQuery(Categoria.class);
		Root<Categoria> root = criteria.from(Categoria.class);
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		TypedQuery<Categoria> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter, Categoria.class));
	}
	
	@Override
	public Predicate[] criarRestricoes(CategoriaFilter filter, CriteriaBuilder builder, Root<Categoria> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(filter.getId() != null) {
			
			predicates.add(builder.equal(root.get(Categoria_.id), filter.getId()));
		}
		
		if(filter.getDescricao() != null && !filter.getDescricao().isEmpty()) {
			
			predicates.add(
					builder.like(
							builder.lower(root.get(Categoria_.descricao)), 
							"%" + filter.getDescricao().toLowerCase() + "%")
					);
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public List<Categoria> categoriasIniciasUserAnonimo(List<String> descricoes) {
		
		//CriteriaBuilder builder = manager.getCriteriaBuilder();
		String qlString = "select c from Categoria c where c.descricao IN :descricoes"; 
		TypedQuery<Categoria> query = manager.createQuery(qlString, Categoria.class);
		query.setParameter("descricao", descricoes);
		
		return query.getResultList();
	}

}
