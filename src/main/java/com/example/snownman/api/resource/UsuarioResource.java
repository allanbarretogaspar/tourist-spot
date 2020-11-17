package com.example.snownman.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.snownman.api.dto.UsuarioDTO;
import com.example.snownman.api.mapper.UsuarioMapper;
import com.example.snownman.api.models.Usuario;
import com.example.snownman.api.models.enums.Perfil;
import com.example.snownman.api.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService userService;
	
	@GetMapping
	public List<Usuario> listar(){
		
		return this.userService.listar();
	}
	
	
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> criar(@RequestBody UsuarioDTO dto, HttpServletResponse response){
		
	
		//Usuario usuario = UsuarioMapper.INSTANCE.converterParaEntidade(dto);
		Usuario usuario = new Usuario(null, "admin", "allanbarretogaspar@gmail.com", "admin");
		usuario.addPerfil(Perfil.ADMIN);
		Usuario salvo = this.userService.criar(usuario);
		UsuarioDTO ret = UsuarioMapper.INSTANCE.converterParaDto(salvo);
		ResponseEntity<UsuarioDTO> resposta   = ResponseEntity.status(HttpStatus.CREATED).body(ret);
		
		return resposta;
	}
	

}
