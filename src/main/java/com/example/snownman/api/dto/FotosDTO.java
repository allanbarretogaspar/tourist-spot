package com.example.snownman.api.dto;

import java.io.InputStream;

public class FotosDTO {
	
	private Long id;
	private String descricao;
	private byte[] foto;
	private Long pontoTuristicoId;
	private InputStream arquivo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public Long getPontoTuristicoId() {
		return pontoTuristicoId;
	}
	public void setPontoTuristicoId(Long pontoTuristicoId) {
		this.pontoTuristicoId = pontoTuristicoId;
	}
	public InputStream getArquivo() {
		return arquivo;
	}
	public void setArquivo(InputStream arquivo) {
		this.arquivo = arquivo;
	}
	
	
	
	

}
