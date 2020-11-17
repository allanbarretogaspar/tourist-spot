package com.example.snownman.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.snownman.api.models.Usuario;
import com.example.snownman.api.repository.UsuarioRepository;
import com.example.snownman.api.security.UserSS;


@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = this.usuarioRepository.findByLogin(username);

		if (usuario == null) {

			throw new UsernameNotFoundException(username);
		}
		/*Set<Perfil> perfis = new HashSet<>();
		perfis.add(Perfil.ADMIN);*/

		return new UserSS(usuario.getId().intValue(), usuario.getLogin(), usuario.getSenha(), usuario.getPerfis());
	}

}
