package com.example.trab_final.model;

import java.util.Date;
import java.util.List;

public class Emprestimo {
	private long id;
	private Date dataEmprestimo;
	private Date dataPrevista;
	private long matricula;
    private List<ItemEmprestimo> itens;
	
	public Emprestimo() {}

	public Emprestimo(Date dataEmprestimo, Date dataPrevista, long matricula, List<ItemEmprestimo> itens) {
		this.dataEmprestimo = dataEmprestimo;
		this.dataPrevista = dataPrevista;
		this.matricula = matricula;
		this.itens = itens;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public long getMatricula() {
		return matricula;
	}

	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}

	public List<ItemEmprestimo> getItens() {
		return itens;
	}

	public void setItens(List<ItemEmprestimo> itens) {
		this.itens = itens;
	}

}
