package com.example.gymarena.repository;

import com.example.gymarena.model.Ejercicio;
import com.example.gymarena.model.Estadistica;
import com.example.gymarena.model.Usuario;

import java.util.List;

public class EstadisticaRepository {
    public EstadisticaRepository() {}

    public void compararEstadistica(EstadisticaDAO estadisticaDAO, Usuario currentUser, Usuario amigo, Ejercicio ejercicio, ComparacionCallback callback) {
        // Mi estadistica
        estadisticaDAO.obtenerUltimaEstadisticaPorEjercicio(currentUser.getIdUsuario(), ejercicio.getIdEjercicio(), new DAOInterface.OnTodosObtenidos<Estadistica>() {
                    @Override
                    public void onSuccess(List<Estadistica> listaUser) {

                        if (listaUser == null || listaUser.isEmpty()) {
                            callback.onError(new Exception("El usuario no tiene estadísticas"));
                            return;
                        }

                        Estadistica estadisticaUser = listaUser.get(0);
                        //Estadistica de mi amigo:
                        estadisticaDAO.obtenerUltimaEstadisticaPorEjercicio(amigo.getIdUsuario(), ejercicio.getIdEjercicio(), new DAOInterface.OnTodosObtenidos<Estadistica>() {
                                    @Override
                                    public void onSuccess(List<Estadistica> listaAmigo) {

                                        if (listaAmigo == null || listaAmigo.isEmpty()) {
                                            callback.onError(new Exception("El amigo no tiene estadísticas"));
                                            return;
                                        }

                                        Estadistica estadisticaAmigo = listaAmigo.get(0);

                                        // Comparación
                                        StringBuilder resultado = new StringBuilder();

                                        if (estadisticaUser.getPeso() > estadisticaAmigo.getPeso()) {
                                            resultado.append("Tú levantas más peso.\n");
                                        } else if (estadisticaUser.getPeso() < estadisticaAmigo.getPeso()) {
                                            resultado.append("Tu amigo levanta más peso.\n");
                                        } else {
                                            resultado.append("Ambos levantáis el mismo peso.\n");
                                        }

                                        if (estadisticaUser.getRepeticionesTotales() > estadisticaAmigo.getRepeticionesTotales()) {
                                            resultado.append("Tú haces más repeticiones.");
                                        } else if (estadisticaUser.getRepeticionesTotales() < estadisticaAmigo.getRepeticionesTotales()) {
                                            resultado.append("Tu amigo hace más repeticiones.");
                                        } else {
                                            resultado.append("Ambos hacéis las mismas repeticiones.");
                                        }

                                        callback.onResultado(resultado.toString());
                                    }

                                    @Override
                                    public void onFailure(Exception e) {
                                        callback.onError(e);
                                    }
                                }
                        );
                    }

                    @Override
                    public void onFailure(Exception e) {
                        callback.onError(e);
                    }
                }
        );
    }

    public interface ComparacionCallback {
        void onResultado(String resultado);
        void onError(Exception e);
    }
}
