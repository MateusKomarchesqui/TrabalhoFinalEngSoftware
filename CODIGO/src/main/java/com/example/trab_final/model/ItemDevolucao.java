package com.example.trab_final.model;

import java.util.Date;

public class ItemDevolucao {
    private long id;
    private long devolucao_id;
    private Livro livro;
    private Date dataDevolucao;
    private int diasAtraso;

    public ItemDevolucao() {
    }

    public ItemDevolucao(long id, long devolucao_id, Livro livro, Date dataDevolucao, int diasAtraso) {
        this.id = id;
        this.devolucao_id = devolucao_id;
        this.livro = livro;
        this.dataDevolucao = dataDevolucao;
        this.diasAtraso = diasAtraso;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDevolucao_id() {
        return this.devolucao_id;
    }

    public void setDevolucao_id(long devolucao_id) {
        this.devolucao_id = devolucao_id;
    }

    public Livro getLivro() {
        return this.livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Date getDataDevolucao() {
        return this.dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getDiasAtraso() {
        return this.diasAtraso;
    }

    public void setDiasAtraso(int diasAtraso) {
        this.diasAtraso = diasAtraso;
    }
}
