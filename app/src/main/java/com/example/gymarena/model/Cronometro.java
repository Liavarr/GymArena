package com.example.gymarena.model;

public class Cronometro {
    private long tiempoEjercicio;
    private long tiempoDescanso;
    private boolean enCurso;
    private long cantidadPausa;

    public Cronometro() {
    }

    public Cronometro(long tiempoEjercicio, long tiempoDescanso, boolean enCurso, long cantidadPausa) {
        this.tiempoEjercicio = tiempoEjercicio;
        this.tiempoDescanso = tiempoDescanso;
        this.enCurso = enCurso;
        this.cantidadPausa = cantidadPausa;
    }

    public long getTiempoEjercicio() {
        return tiempoEjercicio;
    }

    public void setTiempoEjercicio(long tiempoEjercicio) {
        this.tiempoEjercicio = tiempoEjercicio;
    }

    public long getTiempoDescanso() {
        return tiempoDescanso;
    }

    public void setTiempoDescanso(long tiempoDescanso) {
        this.tiempoDescanso = tiempoDescanso;
    }

    public boolean isEnCurso() {
        return enCurso;
    }

    public void setEnCurso(boolean enCurso) {
        this.enCurso = enCurso;
    }

    public long getCantidadPausa() {
        return cantidadPausa;
    }

    public void setCantidadPausa(long cantidadPausa) {
        this.cantidadPausa = cantidadPausa;
    }

    @Override
    public String toString() {
        return "Cronometro{" +
                "tiempoEjercicio=" + tiempoEjercicio +
                ", tiempoDescanso=" + tiempoDescanso +
                ", enCurso=" + enCurso +
                ", cantidadPausa=" + cantidadPausa +
                '}';
    }
}
