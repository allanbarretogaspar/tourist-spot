package com.example.snownman.api.repository.PontoTuristico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.snownman.api.dto.filters.PontoTuristicoFilter;
import com.example.snownman.api.models.PontoTuristico;
import com.example.snownman.api.models.PontoTuristico_;
import com.example.snownman.api.models.enums.CategoriasIniciais;
import com.example.snownman.api.repository.AbstractRepositoryQuery;


public class PontoTuristicoRepositoryQueryImpl  extends AbstractRepositoryQuery<PontoTuristico, PontoTuristicoFilter> implements PontoTuristicoRepositoryQuery {

	@Override
	public Page<PontoTuristico> filtrar(PontoTuristicoFilter filter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<PontoTuristico> criteria = builder.createQuery(PontoTuristico.class);
		Root<PontoTuristico> root = criteria.from(PontoTuristico.class);
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		TypedQuery<PontoTuristico> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter, PontoTuristico.class));
	}
	
	@Override
	public Predicate[] criarRestricoes(PontoTuristicoFilter filter, CriteriaBuilder builder, Root<PontoTuristico> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(filter.getId() != null) {
			
			predicates.add(builder.equal(root.get(PontoTuristico_.id), filter.getId()));
		}
		
		if(filter.getNome() != null && !filter.getNome().isEmpty()) {
			
			predicates.add(
					builder.like(
							builder.lower(root.get(PontoTuristico_.nome)), 
							"%" + filter.getNome().toLowerCase() + "%")
					);
		}
		
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public Page<PontoTuristico> filtrarAnonimo(PontoTuristicoFilter filter, Pageable pageable) {
		
		List<CategoriasIniciais> lista = Arrays.asList(CategoriasIniciais.values());
		List<String> descricoesCategorias = new ArrayList<String>();
		for(CategoriasIniciais c : lista) {
			
			descricoesCategorias.add(c.getDescricao());
		}
		String query = "SELECT p FROM PontoTuristico p  WHERE p.descricao in :lista";
		TypedQuery<PontoTuristico> consulta = manager.createQuery(query, PontoTuristico.class);
		consulta.setParameter("lista", descricoesCategorias);
		adicionarRestricoesDePaginacao(consulta, pageable);
		return new PageImpl<>(consulta.getResultList(), pageable, total(filter, PontoTuristico.class));
	}

}
