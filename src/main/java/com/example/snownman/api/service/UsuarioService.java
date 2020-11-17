package com.example.snownman.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.snownman.api.models.Usuario;
import com.example.snownman.api.repository.UsuarioRepository;

@Service
public class UsuarioService extends AbstractService<Usuario>  {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public Usuario criar(Usuario entidade) {
		
		entidade.setSenha(bCryptPasswordEncoder.encode(entidade.getSenha()));
		Usuario entidadeSalva = repository.save(entidade);
		return entidadeSalva;
		
	}

	

}
