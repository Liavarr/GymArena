package com.example.gymarena.repository;

import android.util.Log;

import com.example.gymarena.model.Estadistica;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstadisticaDAO implements DAOInterface<Estadistica>{
    private final FirebaseFirestore db;

    public EstadisticaDAO(FirebaseFirestore instancia) {
        Log.d("EstadisticaDAO", "Instancia obtenida y conexion lista");
        this.db = instancia;
    }

    // CREAR
    @Override
    public void crear(Estadistica estadistica, OnCreado<Estadistica> listener) {
        db.collection("estadisticas")
                .add(estadistica)
                .addOnSuccessListener(doc -> listener.onSuccess(doc.getId()))
                .addOnFailureListener(listener::onFailure);
    }

    // OBTENER UNO
    @Override
    public void obtenerUno(String idDocumento, OnObtenido<Estadistica> listener) {
        db.collection("estadisticas")
                .document(idDocumento)
                .get()
                .addOnSuccessListener(doc -> {
                    if (doc.exists()) {
                        Estadistica estadistica = doc.toObject(Estadistica.class);
                        estadistica.setIdEstadistica(doc.getId());
                        listener.onSuccess(estadistica);
                    } else {
                        listener.onFailure(new Exception("Estadística no encontrada"));
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }

    // OBTENER TODO
    @Override
    public void obtenerTodo(OnTodosObtenidos<Estadistica> listener) {
        db.collection("estadisticas")
                .get()
                .addOnSuccessListener(query -> {
                    List<Estadistica> lista = new ArrayList<>();
                    for (DocumentSnapshot doc : query.getDocuments()) {
                        Estadistica e = doc.toObject(Estadistica.class);
                        // Asignar el ID de Firestore al objeto
                        e.setIdEstadistica(doc.getId());
                        lista.add(e);
                    }
                    listener.onSuccess(lista);
                })
                .addOnFailureListener(listener::onFailure);
    }

    public void obtenerUltimaEstadisticaPorEjercicio(String idUsuario, String idEjercicio, OnTodosObtenidos<Estadistica> listener) {
        db.collection("estadisticas")
                .whereEqualTo("idUsuario", idUsuario)
                .whereEqualTo("idEjercicio", idEjercicio)
                .orderBy("fecha", Query.Direction.DESCENDING)
                .limit(1) // Solo nos interesa la más reciente
                .get()
                .addOnSuccessListener(query -> {
                    if (!query.isEmpty()) {
                        DocumentSnapshot doc = query.getDocuments().get(0);
                        Estadistica e = doc.toObject(Estadistica.class);
                        e.setIdEstadistica(doc.getId());
                        listener.onSuccess(Collections.singletonList(e)); // devolvemos como lista de 1 elemento
                    } else {
                        listener.onSuccess(new ArrayList<>()); // lista vacia si no hay datos
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }


    // ACTUALIZAR
    @Override
    public void actualizar(String idDocumento, Estadistica estadistica, OnActualizado listener) {
        db.collection("estadisticas")
                .document(idDocumento)
                .set(estadistica)
                .addOnSuccessListener(unused -> listener.onSuccess())
                .addOnFailureListener(listener::onFailure);
    }

    // ELIMINAR
    @Override
    public void eliminar(String idDocumento, OnEliminado listener) {
        db.collection("estadisticas")
                .document(idDocumento)
                .delete()
                .addOnSuccessListener(unused -> listener.onSuccess())
                .addOnFailureListener(listener::onFailure);
    }
}
