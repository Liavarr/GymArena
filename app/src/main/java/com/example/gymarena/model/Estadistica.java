package com.example.gymarena.model;

import java.util.Date;

public class Estadistica {
    private String idEstadistica;
    private String idUsuario;
    private String idEjercicio;
    private Date fecha;
    private double peso;
    private int repeticionesTotales;

    public Estadistica() {
    }

    public Estadistica(String idUsuario, String idEjercicio, Date fecha, double peso, int repeticionesTotales) {
        this.idUsuario = idUsuario;
        this.idEjercicio = idEjercicio;
        this.fecha = fecha;
        this.peso = peso;
        this.repeticionesTotales = repeticionesTotales;
    }

    public String getIdEstadistica() {
        return idEstadistica;
    }

    public void setIdEstadistica(String idEstadistica) {
        this.idEstadistica = idEstadistica;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(String idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getRepeticionesTotales() {
        return repeticionesTotales;
    }

    public void setRepeticionesTotales(int repeticionesTotales) {
        this.repeticionesTotales = repeticionesTotales;
    }

    @Override
    public String toString() {
        return "Estadistica{" +
                "idUsuario='" + idUsuario + '\'' +
                ", idEjercicio='" + idEjercicio + '\'' +
                ", fecha=" + fecha +
                ", peso=" + peso +
                ", repeticionesTotales=" + repeticionesTotales +
                '}';
    }
}
