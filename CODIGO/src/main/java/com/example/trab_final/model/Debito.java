package com.example.trab_final.model;

import java.util.Date;

public class Debito {
	private long id;
	private long matricula;
	private Date data;
	private float valor;

	public Debito() {}
	
	public Debito(long aluno, Date data, float valor){
		this.matricula =aluno;
		this.data = data;
		this.valor = valor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMatricula() {
		return matricula;
	}

	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
}