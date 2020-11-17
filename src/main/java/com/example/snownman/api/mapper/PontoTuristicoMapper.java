package com.example.snownman.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.snownman.api.dto.PontoTuristicoDTO;
import com.example.snownman.api.models.PontoTuristico;

@Mapper()
public interface PontoTuristicoMapper {
	
	PontoTuristicoMapper INSTANCE = Mappers.getMapper(PontoTuristicoMapper.class);
	
	@Mapping(source = "categoriaId", target = "categoria.id")
	PontoTuristico converterParaEntidade(PontoTuristicoDTO dto);
	
	@Mapping(source = "categoria.id", target = "categoriaId")
	PontoTuristicoDTO converterParaDto(PontoTuristico entidade);
	
	List<PontoTuristicoDTO> converterColecaoParaDto(List<PontoTuristico> lista);

}
