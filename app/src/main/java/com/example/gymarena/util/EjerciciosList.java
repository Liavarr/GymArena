package com.example.gymarena.util;

import com.example.gymarena.model.Ejercicio;

import java.util.ArrayList;
import java.util.Arrays;

public class EjerciciosList {
    public static ArrayList<Ejercicio> getEjerciciosReferencia() {
        ArrayList<Ejercicio> ejerciciosReferencia = new ArrayList<>();

// Pecho
        Ejercicio e1 = new Ejercicio("Press de banca", "Pecho", Arrays.asList("Tríceps", "Hombros"));
        e1.setIdEjercicio("E001");
        ejerciciosReferencia.add(e1);

        Ejercicio e2 = new Ejercicio("Press inclinado con mancuernas", "Pecho", Arrays.asList("Tríceps", "Hombros"));
        e2.setIdEjercicio("E002");
        ejerciciosReferencia.add(e2);

        Ejercicio e3 = new Ejercicio("Fondos en paralelas", "Pecho", Arrays.asList("Tríceps", "Hombros"));
        e3.setIdEjercicio("E003");
        ejerciciosReferencia.add(e3);

        Ejercicio e4 = new Ejercicio("Aperturas con mancuernas", "Pecho", Arrays.asList("Hombros"));
        e4.setIdEjercicio("E004");
        ejerciciosReferencia.add(e4);

// Espalda
        Ejercicio e5 = new Ejercicio("Remo con barra", "Espalda", Arrays.asList("Bíceps", "Hombros"));
        e5.setIdEjercicio("E005");
        ejerciciosReferencia.add(e5);

        Ejercicio e6 = new Ejercicio("Pull-ups asistidos", "Espalda", Arrays.asList("Bíceps", "Hombros"));
        e6.setIdEjercicio("E006");
        ejerciciosReferencia.add(e6);

        Ejercicio e7 = new Ejercicio("Jalón al pecho con polea", "Espalda", Arrays.asList("Bíceps", "Hombros"));
        e7.setIdEjercicio("E007");
        ejerciciosReferencia.add(e7);

        Ejercicio e8 = new Ejercicio("Remo con mancuernas", "Espalda", Arrays.asList("Bíceps", "Hombros"));
        e8.setIdEjercicio("E008");
        ejerciciosReferencia.add(e8);

// Hombros
        Ejercicio e9 = new Ejercicio("Press militar con barra", "Hombros", Arrays.asList("Tríceps"));
        e9.setIdEjercicio("E009");
        ejerciciosReferencia.add(e9);

        Ejercicio e10 = new Ejercicio("Elevaciones laterales", "Hombros", Arrays.asList("Trapecio"));
        e10.setIdEjercicio("E010");
        ejerciciosReferencia.add(e10);

        Ejercicio e11 = new Ejercicio("Elevaciones frontales", "Hombros", Arrays.asList("Trapecio"));
        e11.setIdEjercicio("E011");
        ejerciciosReferencia.add(e11);

// Brazos
        Ejercicio e12 = new Ejercicio("Curl de bíceps con barra", "Bíceps", Arrays.asList("Antebrazos"));
        e12.setIdEjercicio("E012");
        ejerciciosReferencia.add(e12);

        Ejercicio e13 = new Ejercicio("Curl de bíceps con mancuernas", "Bíceps", Arrays.asList("Antebrazos"));
        e13.setIdEjercicio("E013");
        ejerciciosReferencia.add(e13);

        Ejercicio e14 = new Ejercicio("Extensión de tríceps en polea", "Tríceps", Arrays.asList("Hombros"));
        e14.setIdEjercicio("E014");
        ejerciciosReferencia.add(e14);

        Ejercicio e15 = new Ejercicio("Fondos para tríceps", "Tríceps", Arrays.asList("Hombros", "Pecho"));
        e15.setIdEjercicio("E015");
        ejerciciosReferencia.add(e15);

// Piernas
        Ejercicio e16 = new Ejercicio("Sentadillas con barra", "Cuádriceps", Arrays.asList("Glúteos", "Isquiotibiales"));
        e16.setIdEjercicio("E016");
        ejerciciosReferencia.add(e16);

        Ejercicio e17 = new Ejercicio("Prensa de piernas", "Cuádriceps", Arrays.asList("Glúteos", "Isquiotibiales"));
        e17.setIdEjercicio("E017");
        ejerciciosReferencia.add(e17);

        Ejercicio e18 = new Ejercicio("Zancadas con mancuernas", "Cuádriceps", Arrays.asList("Glúteos", "Isquiotibiales"));
        e18.setIdEjercicio("E018");
        ejerciciosReferencia.add(e18);

        Ejercicio e19 = new Ejercicio("Curl de piernas tumbado", "Isquiotibiales", Arrays.asList("Glúteos"));
        e19.setIdEjercicio("E019");
        ejerciciosReferencia.add(e19);

// Core / Abdomen
        Ejercicio e20 = new Ejercicio("Crunch abdominal", "Abdomen", Arrays.asList());
        e20.setIdEjercicio("E020");
        ejerciciosReferencia.add(e20);

        Ejercicio e21 = new Ejercicio("Plancha frontal", "Abdomen", Arrays.asList("Espalda"));
        e21.setIdEjercicio("E021");
        ejerciciosReferencia.add(e21);

        Ejercicio e22 = new Ejercicio("Elevación de piernas colgado", "Abdomen", Arrays.asList("Cadera"));
        e22.setIdEjercicio("E022");
        ejerciciosReferencia.add(e22);

        Ejercicio e23 = new Ejercicio("Russian twists con mancuerna", "Abdomen", Arrays.asList("Oblicuos"));
        e23.setIdEjercicio("E023");
        ejerciciosReferencia.add(e23);

        return ejerciciosReferencia;

    }
}
