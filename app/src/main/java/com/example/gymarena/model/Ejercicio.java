package com.example.gymarena.model;

import java.util.List;

public class Ejercicio {
    private String idEjercicio;
    private String nombre;
    private String musculoPrincipal;
    private List<String> musculoSecundario;
    public Ejercicio(){

    }

    public Ejercicio(String nombre, String musculoPrincipal, List<String> musculoSecundario) {
        this.nombre = nombre;
        this.musculoPrincipal = musculoPrincipal;
        this.musculoSecundario = musculoSecundario;
    }

    public String getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(String idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMusculoPrincipal() {
        return musculoPrincipal;
    }

    public void setMusculoPrincipal(String musculoPrincipal) {
        this.musculoPrincipal = musculoPrincipal;
    }

    public List<String> getMusculoSecundario() {
        return musculoSecundario;
    }

    public void setMusculoSecundario(List<String> musculoSecundario) {
        this.musculoSecundario = musculoSecundario;
    }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "idEjercicio='" + idEjercicio + '\'' +
                ", nombre='" + nombre + '\'' +
                ", musculoPrincipal='" + musculoPrincipal + '\'' +
                ", musculoSecundario=" + musculoSecundario +
                '}';
    }
}
