package com.example.gymarena.repository;

import com.example.gymarena.model.Ejercicio;
import com.example.gymarena.model.Estadistica;
import com.example.gymarena.model.Usuario;

import java.util.List;

public class EstadisticaRepository {
    public EstadisticaRepository(){
    }

    public void compararEstadistica(EstadisticaDAO estadisticaDAO, Usuario currentUser, Usuario amigo, Ejercicio ejercicio){
        //Mi estadistica:
        estadisticaDAO.obtenerUltimaEstadisticaPorEjercicio(currentUser.getIdUsuario(), ejercicio.getIdEjercicio(), new DAOInterface.OnTodosObtenidos<Estadistica>(){
            @Override
            public void onSuccess(List<Estadistica> lista) {

            }
            @Override
            public void onFailure(Exception e) {

            }
        });
        // Estadistica de amigo:
        estadisticaDAO.obtenerUltimaEstadisticaPorEjercicio(amigo.getIdUsuario(), ejercicio.getIdEjercicio(), new DAOInterface.OnTodosObtenidos<Estadistica>(){
            @Override
            public void onSuccess(List<Estadistica> lista) {

            }
            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
