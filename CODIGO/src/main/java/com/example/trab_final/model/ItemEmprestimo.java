package com.example.trab_final.model;

import java.util.Date;

public class ItemEmprestimo {
	private long id;
	private long emprestimo_id;
	private Livro livro;
    private Date dataDevolucao;
	private Date dataPrevista;

	public ItemEmprestimo() {}
    
	public ItemEmprestimo(Livro livro, Date dataDevolucao, Date dataPrevista) {
		this.livro = livro;
		this.dataDevolucao = dataDevolucao;
		this.dataPrevista = dataPrevista;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEmprestimo_id() {
		return emprestimo_id;
	}

	public void setEmprestimo_id(long emprestimo_id) {
		this.emprestimo_id = emprestimo_id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

}
