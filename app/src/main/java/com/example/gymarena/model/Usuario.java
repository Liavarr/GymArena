package com.example.gymarena.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private double peso;
    private double altura;
    private List<String> listaAmigos;

    public Usuario() {
        this.listaAmigos = new ArrayList<>();
    }

    public Usuario(String id, String nombre, String email, double peso, double altura) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.peso = peso;
        this.altura = altura;
        this.listaAmigos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public List<String> getListaAmigos() {
        return listaAmigos;
    }

    public void setListaAmigos(List<String> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", peso=" + peso +
                ", altura=" + altura +
                ", listaAmigos=" + listaAmigos +
                '}';
    }
}
