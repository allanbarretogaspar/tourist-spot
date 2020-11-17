package com.example.snownman.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.snownman.api.dto.filters.PontoTuristicoFilter;
import com.example.snownman.api.models.PontoTuristico;
import com.example.snownman.api.repository.PontoTuristicoRepository;

@Service
public class PontoTuristicoService extends AbstractService<PontoTuristico> {

	@Autowired
	private PontoTuristicoRepository pontoTuristicoRepository;

	public Page<PontoTuristico> filtrar(PontoTuristicoFilter filter, Pageable pageable) {

		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();

		if (authentication.getName().equalsIgnoreCase("anonymousUser")) {

			return this.pontoTuristicoRepository.filtrarAnonimo(filter, pageable);
		} else {

			return this.pontoTuristicoRepository.filtrar(filter, pageable);
		}

	}

}
