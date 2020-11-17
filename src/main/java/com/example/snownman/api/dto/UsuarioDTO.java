package com.example.snownman.api.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.snownman.api.models.enums.Perfil;

public class UsuarioDTO {

	private Long id;
	private String login;
	private String email;
	private String senha;
	private Set<Perfil> perfis = new HashSet<>();

	
	

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	

}
