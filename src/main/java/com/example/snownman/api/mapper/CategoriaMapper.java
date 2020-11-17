package com.example.snownman.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.snownman.api.dto.CategoriaDTO;
import com.example.snownman.api.models.Categoria;

@Mapper
public interface CategoriaMapper {
	
	CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);
	
	Categoria converterParaEntidade(CategoriaDTO dto);
	
	CategoriaDTO converterParaDto(Categoria entidade);
	
	List<CategoriaDTO> converterColecaoParaDto(List<Categoria> lista);

}
