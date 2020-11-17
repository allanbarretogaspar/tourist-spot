package com.example.snownman.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.snownman.api.dto.FotosDTO;
import com.example.snownman.api.models.Fotos;

@Mapper
public interface FotosMapper {
	
	FotosMapper INSTANCE = Mappers.getMapper(FotosMapper.class);
	
	@Mapping(source = "pontoTuristicoId", target = "pontoTuristico.id")
	Fotos converterParaEntidade(FotosDTO dto);
	
	@Mapping(source = "pontoTuristico.id", target = "pontoTuristicoId")
	FotosDTO converterParaDto(Fotos entidade);
	
	List<FotosDTO> converterColecaoParaDto(List<Fotos> lista);

}
