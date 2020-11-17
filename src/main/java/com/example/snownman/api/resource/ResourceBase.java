package com.example.snownman.api.resource;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ResourceBase<C, D> {

	protected Page<D> converterPageParaDTO(List<D> content, long totalElements, Pageable pageable) {


		return new PageImpl<D>(content, pageable, totalElements);
	}
}
