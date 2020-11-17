package com.example.snownman.api.repository.favoritos;

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

import com.example.snownman.api.dto.filters.FavoritosFilter;
import com.example.snownman.api.models.Favoritos;
import com.example.snownman.api.models.Favoritos_;
import com.example.snownman.api.models.Usuario_;
import com.example.snownman.api.repository.AbstractRepositoryQuery;


public class FavoritosRepositoryQueryImpl  extends AbstractRepositoryQuery<Favoritos, FavoritosFilter> implements FavoritosRepositoryQuery {

	@Override
	public Page<Favoritos> filtrar(FavoritosFilter filter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Favoritos> criteria = builder.createQuery(Favoritos.class);
		Root<Favoritos> root = criteria.from(Favoritos.class);
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		TypedQuery<Favoritos> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter, Favoritos.class));
	}
	
	@Override
	public Predicate[] criarRestricoes(FavoritosFilter filter, CriteriaBuilder builder, Root<Favoritos> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(filter.getUsuarioId() != null) {
			
			predicates.add(builder.equal(root.get(Favoritos_.usuario).get(Usuario_.id), filter.getUsuarioId()));
		}
		
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
