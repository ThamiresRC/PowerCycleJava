package br.com.fiap.dtos;

import java.time.LocalDateTime;

public class EnergiaDto {

    private int id;
    private int idBicicleta;
    private double energiaGerada;
    private LocalDateTime dataGeracao;

    public EnergiaDto(int id, int idBicicleta, double energiaGerada, LocalDateTime dataGeracao) {
        this.id = id;
        this.idBicicleta = idBicicleta;
        this.energiaGerada = energiaGerada;
        this.dataGeracao = dataGeracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(int idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public double getEnergiaGerada() {
        return energiaGerada;
    }

    public void setEnergiaGerada(double energiaGerada) {
        this.energiaGerada = energiaGerada;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }
}
