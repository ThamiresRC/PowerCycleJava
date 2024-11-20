package br.com.fiap.models;

import java.time.LocalDateTime;

public class Aluguel {
    private int id, idBicicleta, idUsuario;
    private LocalDateTime inicioAluguel;
    private LocalDateTime fimAluguel;
    private double distanciaPercorrida, tempoDeUso;

    public Aluguel(int id, int idBicicleta, int idUsuario, LocalDateTime inicioAluguel) {
        this.id = id;
        this.idBicicleta = idBicicleta;
        this.idUsuario = idUsuario;
        this.inicioAluguel = inicioAluguel;
    }

    public void encerrarAluguel(LocalDateTime fimAluguel, double distanciaPercorrida) {
        this.fimAluguel = fimAluguel;
        this.distanciaPercorrida = distanciaPercorrida;
        this.tempoDeUso = java.time.Duration.between(inicioAluguel, fimAluguel).toMinutes();
    }

    public double calcularCusto(double taxaPorKm) {
        double custo = this.distanciaPercorrida * taxaPorKm;
        if (tempoDeUso > 60) {
            custo *= 0.9;
        }
        return custo;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getInicioAluguel() {
        return inicioAluguel;
    }

    public void setInicioAluguel(LocalDateTime inicioAluguel) {
        this.inicioAluguel = inicioAluguel;
    }

    public LocalDateTime getFimAluguel() {
        return fimAluguel;
    }

    public void setFimAluguel(LocalDateTime fimAluguel) {
        this.fimAluguel = fimAluguel;
    }

    public double getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public void setDistanciaPercorrida(double distanciaPercorrida) {
        this.distanciaPercorrida = distanciaPercorrida;
    }

    public double getTempoDeUso() {
        return tempoDeUso;
    }

    public void setTempoDeUso(double tempoDeUso) {
        this.tempoDeUso = tempoDeUso;
    }
}