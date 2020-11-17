package com.example.snownman.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favoritos")
public class Favoritos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FAVORITOS")
	private Long id;

	@ManyToOne
    @JoinColumn(name="ID_USUARIO", nullable = false, insertable = true, updatable = true)
    private Usuario usuario;
	
	@ManyToOne
    @JoinColumn(name="ID_PONTO_TURISTICO", nullable = false, insertable = true, updatable = true)
    private PontoTuristico pontoTuristico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PontoTuristico getPontoTuristico() {
		return pontoTuristico;
	}

	public void setPontoTuristico(PontoTuristico pontoTuristico) {
		this.pontoTuristico = pontoTuristico;
	}
	
	

}
