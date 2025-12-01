package com.example.gymarena.model;

import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private String idRutina;
    private String idUsuario;
    private String nombre;
    private String descripcion;
    private List<String> ejercicios;

    public Rutina() {
        this.ejercicios = new ArrayList<>();
    }

    public Rutina(String idRutina,String idUsuario, String nombre, String descripcion) {
        this.idRutina = idRutina;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ejercicios = new ArrayList<>();
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

    public List<String> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<String> ejercicios) {
        this.ejercicios = ejercicios;
    }

    @Override
    public String toString() {
        return "Rutina{" +
                "idRutina='" + idRutina + '\'' +
                ", nombre='" + nombre + '\'' +
                ", Descripcion='" + descripcion + '\'' +
                ", ejercicios=" + ejercicios +
                '}';
    }
}
