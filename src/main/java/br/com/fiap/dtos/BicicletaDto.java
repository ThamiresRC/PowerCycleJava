package br.com.fiap.dtos;

public class BicicletaDto {

    private int id;
    private String modelo;
    private boolean disponivel;
    private double energiaAcumulada;

    public BicicletaDto(int id, String modelo, boolean disponivel, double energiaAcumulada) {
        this.id = id;
        this.modelo = modelo;
        this.disponivel = disponivel;
        this.energiaAcumulada = energiaAcumulada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public double getEnergiaAcumulada() {
        return energiaAcumulada;
    }

    public void setEnergiaAcumulada(double energiaAcumulada) {
        this.energiaAcumulada = energiaAcumulada;
    }

}
