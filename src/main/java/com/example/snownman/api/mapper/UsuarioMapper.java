package com.example.snownman.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.snownman.api.dto.UsuarioDTO;
import com.example.snownman.api.models.Usuario;

@Mapper
public interface UsuarioMapper {
	
	UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
	
	Usuario converterParaEntidade(UsuarioDTO dto);
	
	UsuarioDTO converterParaDto(Usuario entidade);
	
	List<UsuarioDTO> converterColecaoParaDto(List<Usuario> lista);

}
