package com.example.snownman.api.models.enums;

public enum CategoriasIniciais {
	
	MUSEU(1, "Museu"), 
	PARQUE(2, "Parque"), 
	TEATRO(3, "Teatro"),
	MONUMENTO(4, "Monumento") ;
	
	private int cod;
	private String descricao;
	
	
	
	private CategoriasIniciais(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static CategoriasIniciais toEnum(Integer cod) {

		if (cod == null) {

			return null;
		}

		for (CategoriasIniciais x : CategoriasIniciais.values()) {

			if (cod.equals(x.getCod())) {

				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
