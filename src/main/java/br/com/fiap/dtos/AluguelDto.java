package br.com.fiap.dtos;

import java.time.LocalDateTime;

public class AluguelDto {

    private int id;
    private int idBicicleta;
    private int idUsuario;
    private LocalDateTime inicioAluguel;
    private LocalDateTime fimAluguel;
    private double distanciaPercorrida;
    private double tempoDeUso;

    public AluguelDto(int id, int idBicicleta, int idUsuario, LocalDateTime inicioAluguel, LocalDateTime fimAluguel, double distanciaPercorrida, double tempoDeUso) {
        this.id = id;
        this.idBicicleta = idBicicleta;
        this.idUsuario = idUsuario;
        this.inicioAluguel = inicioAluguel;
        this.fimAluguel = fimAluguel;
        this.distanciaPercorrida = distanciaPercorrida;
        this.tempoDeUso = tempoDeUso;
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
