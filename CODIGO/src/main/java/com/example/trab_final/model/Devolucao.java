package com.example.trab_final.model;

import java.util.List;

public class Devolucao {
    private long id;
	private long emprestimo_id;
    private float valorMulta;
    private List<ItemDevolucao> itens;

    public Devolucao() {
    }

    public Devolucao(long emprestimo_id, List<ItemDevolucao> itens) {
        this.emprestimo_id = emprestimo_id;
        this.itens = itens;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmprestimo_id() {
        return this.emprestimo_id;
    }

    public void setEmprestimo_id(long emprestimo_id) {
        this.emprestimo_id = emprestimo_id;
    }

    public float getValorMulta() {
        return this.valorMulta;
    }

    public void setValorMulta(float valorMulta) {
        this.valorMulta = valorMulta;
    }

    public List<ItemDevolucao> getItens() {
        return this.itens;
    }

    public void setItens(List<ItemDevolucao> itens) {
        this.itens = itens;
    }
}
