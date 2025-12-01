package com.example.gymarena.util;

import com.example.gymarena.model.Ejercicio;

import java.util.ArrayList;
import java.util.Arrays;

public class EjerciciosList {
    public static ArrayList<Ejercicio> getEjerciciosReferencia() {
        ArrayList<Ejercicio> ejerciciosReferencia = new ArrayList<>();

        // Pecho
        ejerciciosReferencia.add(new Ejercicio("Press de banca", "Pecho", Arrays.asList("Tríceps", "Hombros")));
        ejerciciosReferencia.add(new Ejercicio("Press inclinado con mancuernas", "Pecho", Arrays.asList("Tríceps", "Hombros")));
        ejerciciosReferencia.add(new Ejercicio("Fondos en paralelas", "Pecho", Arrays.asList("Tríceps", "Hombros")));
        ejerciciosReferencia.add(new Ejercicio("Aperturas con mancuernas", "Pecho", Arrays.asList("Hombros")));

        // Espalda
        ejerciciosReferencia.add(new Ejercicio("Remo con barra", "Espalda", Arrays.asList("Bíceps", "Hombros")));
        ejerciciosReferencia.add(new Ejercicio("Pull-ups asistidos", "Espalda", Arrays.asList("Bíceps", "Hombros")));
        ejerciciosReferencia.add(new Ejercicio("Jalón al pecho con polea", "Espalda", Arrays.asList("Bíceps", "Hombros")));
        ejerciciosReferencia.add(new Ejercicio("Remo con mancuernas", "Espalda", Arrays.asList("Bíceps", "Hombros")));

        // Hombros
        ejerciciosReferencia.add(new Ejercicio("Press militar con barra", "Hombros", Arrays.asList("Tríceps")));
        ejerciciosReferencia.add(new Ejercicio("Elevaciones laterales", "Hombros", Arrays.asList("Trapecio")));
        ejerciciosReferencia.add(new Ejercicio("Elevaciones frontales", "Hombros", Arrays.asList("Trapecio")));

        // Brazos
        ejerciciosReferencia.add(new Ejercicio("Curl de bíceps con barra", "Bíceps", Arrays.asList("Antebrazos")));
        ejerciciosReferencia.add(new Ejercicio("Curl de bíceps con mancuernas", "Bíceps", Arrays.asList("Antebrazos")));
        ejerciciosReferencia.add(new Ejercicio("Extensión de tríceps en polea", "Tríceps", Arrays.asList("Hombros")));
        ejerciciosReferencia.add(new Ejercicio("Fondos para tríceps", "Tríceps", Arrays.asList("Hombros", "Pecho")));

        // Piernas
        ejerciciosReferencia.add(new Ejercicio("Sentadillas con barra", "Cuádriceps", Arrays.asList("Glúteos", "Isquiotibiales")));
        ejerciciosReferencia.add(new Ejercicio("Prensa de piernas", "Cuádriceps", Arrays.asList("Glúteos", "Isquiotibiales")));
        ejerciciosReferencia.add(new Ejercicio("Zancadas con mancuernas", "Cuádriceps", Arrays.asList("Glúteos", "Isquiotibiales")));
        ejerciciosReferencia.add(new Ejercicio("Curl de piernas tumbado", "Isquiotibiales", Arrays.asList("Glúteos")));

        // Core / Abdomen
        ejerciciosReferencia.add(new Ejercicio("Crunch abdominal", "Abdomen", Arrays.asList()));
        ejerciciosReferencia.add(new Ejercicio("Plancha frontal", "Abdomen", Arrays.asList("Espalda")));
        ejerciciosReferencia.add(new Ejercicio("Elevación de piernas colgado", "Abdomen", Arrays.asList("Cadera")));
        ejerciciosReferencia.add(new Ejercicio("Russian twists con mancuerna", "Abdomen", Arrays.asList("Oblicuos")));

        return ejerciciosReferencia;
    }
}
