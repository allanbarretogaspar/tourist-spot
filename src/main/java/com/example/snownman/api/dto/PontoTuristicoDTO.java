package com.example.snownman.api.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.snownman.api.models.Fotos;

public class PontoTuristicoDTO {
	
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private List<Fotos> fotos = new ArrayList<Fotos>();
	private Long categoriaId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public List<Fotos> getFotos() {
		return fotos;
	}

	public void setFotos(List<Fotos> fotos) {
		this.fotos = fotos;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	
	
	
	

}
