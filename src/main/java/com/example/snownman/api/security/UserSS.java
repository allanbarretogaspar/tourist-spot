package com.example.snownman.api.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.snownman.api.models.enums.Perfil;


public class UserSS implements UserDetails {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3860433824399490740L;
	
	private Integer id;
	private String login;
	private String senha;
	private Collection<? extends GrantedAuthority> authorites;

	public UserSS() {
		
	}
	
	
	public UserSS(Integer id, String login, String senha, Set<Perfil>perfis) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.authorites = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}



	public Integer getId() {

		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorites;
	}

	@Override
	public String getPassword() {

		return senha;
	}

	@Override
	public String getUsername() {

		return login;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
