package com.example.trab_final.model;


public class Aluno {
	private Long matricula;
	private String nome;
	private long cpf;
	private String endereco;

	public Aluno(Long matricula, String nome, long cpf, String endereco) {
		this.matricula = matricula;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getCpf() {
		return cpf;
	}
	
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
