package com.example.gymarena.model;

import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private String idRutina;
    private String nombre;
    private String descripcion;
    private List<Ejercicio> ejercicios;

    public Rutina() {
        this.ejercicios = new ArrayList<>();
    }

    public Rutina(String idRutina, String nombre, String descripcion) {
        this.idRutina = idRutina;
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

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
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
