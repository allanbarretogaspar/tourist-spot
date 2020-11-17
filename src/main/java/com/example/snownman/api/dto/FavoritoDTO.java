package com.example.snownman.api.dto;

public class FavoritoDTO {
	
	private Long id;
	private Long usuarioId;
	private Long pontoTuristicoId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Long getPontoTuristicoId() {
		return pontoTuristicoId;
	}
	public void setPontoTuristicoId(Long pontoTuristicoId) {
		this.pontoTuristicoId = pontoTuristicoId;
	}
	
	

}
