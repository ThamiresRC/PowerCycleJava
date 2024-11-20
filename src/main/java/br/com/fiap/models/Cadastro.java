package br.com.fiap.models;

import java.time.format.DateTimeFormatter;

public abstract class Cadastro {
    private int id;
    private String dataCriacao;
    private String dataAtualizacao;

    public Cadastro(int id, String dataCriacao) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataCriacao;
    }

    public void atualizarData() {
        this.dataAtualizacao = java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public abstract void salvar();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(String dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
