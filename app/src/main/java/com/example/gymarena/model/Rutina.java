package com.example.gymarena.model;

import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private String idRutina;
    private String idUsuario;
    private String nombre;
    private String descripcion;
    private List<String> ejerciciosId; // <-- Solo IDs de los ejercicios

    public Rutina() {
        this.ejerciciosId = new ArrayList<>();
    }

    public Rutina(String idRutina, String idUsuario, String nombre, String descripcion) {
        this.idRutina = idRutina;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ejerciciosId = new ArrayList<>();
    }

    // Getters y setters
    public List<String> getEjerciciosId() {
        return ejerciciosId;
    }

    public void setEjerciciosId(List<String> ejerciciosId) {
        this.ejerciciosId = ejerciciosId;
    }

    public String getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(String idRutina) {
        this.idRutina = idRutina;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Rutina{" +
                "idRutina='" + idRutina + '\'' +
                ", nombre='" + nombre + '\'' +
                ", Descripcion='" + descripcion + '\'' +
                ", ejercicios=" + ejerciciosId +
                '}';
    }
}
