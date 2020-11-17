package com.example.snownman.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.snownman.api.dto.FavoritoDTO;
import com.example.snownman.api.models.Favoritos;

@Mapper
public interface FavoritoMapper {
	
	FavoritoMapper INSTANCE = Mappers.getMapper(FavoritoMapper.class);
	
	@Mapping(source = "pontoTuristicoId", target = "pontoTuristico.id")
	@Mapping(source = "usuarioId", target = "usuario.id")
	Favoritos converterParaEntidade(FavoritoDTO dto);
	
	@Mapping(source = "pontoTuristico.id", target = "pontoTuristicoId")
	@Mapping(source = "usuario.id", target = "usuarioId")
	FavoritoDTO converterParaDto(Favoritos entidade);
	
	List<FavoritoDTO> converterColecaoParaDto(List<Favoritos> lista);

}
