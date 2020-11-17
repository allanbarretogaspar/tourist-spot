package com.example.snownman.api.models;

import java.io.IOException;
import java.io.InputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.snownman.api.util.BASE64DecodedMultipartFile;

@Entity
@Table(name = "fotos")
public class Fotos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String descricao;
	
	@Column
	private byte[] foto;
	
	@ManyToOne
	@JoinColumn(name = "ponto_turistico_id", nullable = false, insertable = true, updatable = true)
	private PontoTuristico pontoTuristico;
	
	@Transient
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

	public PontoTuristico getPontoTuristico() {
		return pontoTuristico;
	}

	public void setPontoTuristico(PontoTuristico pontoTuristico) {
		this.pontoTuristico = pontoTuristico;
	}

	public InputStream getArquivo() {
		
		try {
			BASE64DecodedMultipartFile base = new BASE64DecodedMultipartFile(getFoto());
			this.arquivo = base.getInputStream();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return arquivo;
	}

	public void setArquivo(InputStream arquivo) {
		this.arquivo = arquivo;
	}

	
	
	

}
