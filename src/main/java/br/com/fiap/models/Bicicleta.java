package br.com.fiap.models;

public class Bicicleta {
    private int id;
    private String modelo;
    private boolean disponivel;
    private double energiaAcumulada;

    public Bicicleta(int id, String modelo, boolean disponivel) {
        this.id = id;
        this.modelo = modelo;
        this.disponivel = disponivel;
        this.energiaAcumulada = 0.0;
    }

    public void acumularEnergia(double distanciaPercorrida) {
        double energiaGerada = calcularEnergiaPorDistancia(distanciaPercorrida);
        this.energiaAcumulada += energiaGerada;
    }

    private double calcularEnergiaPorDistancia(double distanciaPercorrida) {
        final double energiaPorKm = 0.05;
        return distanciaPercorrida * energiaPorKm;
    }

    public void resetarEnergia() {
        this.energiaAcumulada = 0.0;
    }

    public boolean estaDisponivel() {
        return disponivel;
    }

    public void alterarDisponibilidade(boolean status) {
        this.disponivel = status;
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
