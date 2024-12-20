package com.example.trab_final.model;

import java.util.List;

public class Titulo {
	private int prazo;
	private String isbn;
	private int edicao;
	private int ano;
	private String editora;
	private int paginas;
	private String nome;
	private Area area;
	private List<Autor> autores;

	public Titulo() {}

	public Titulo(int prazo, String isbn, int edicao, int ano, String editora, int paginas, String nome, Area area, List<Autor> autores) {
		this.prazo = prazo;
		this.isbn = isbn;
		this.edicao = edicao;
		this.ano = ano;
		this.editora = editora;
		this.paginas = paginas;
		this.nome = nome;
		this.area = area;
		this.autores = autores;
	}


	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
}