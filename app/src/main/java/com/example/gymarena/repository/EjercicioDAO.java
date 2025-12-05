package com.example.gymarena.repository;

import android.util.Log;

import com.example.gymarena.model.Ejercicio;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class EjercicioDAO implements DAOInterface<Ejercicio>{
    private final FirebaseFirestore db;

    public EjercicioDAO(FirebaseFirestore instancia) {
        Log.d("EjercicioDAO", "Instancia obtenida y conexion lista");
        this.db = instancia;
    }

    // CREAR
    @Override
    public void crear(Ejercicio ejercicio, OnCreado<Ejercicio> listener) {
        db.collection("ejercicios")
                .add(ejercicio)
                .addOnSuccessListener(docRef -> listener.onSuccess(docRef.getId()))
                .addOnFailureListener(listener::onFailure);
    }

    // OBTENER UNO
    @Override
    public void obtenerUno(String id, OnObtenido<Ejercicio> listener) {
        db.collection("ejercicios")
                .document(id)
                .get()
                .addOnSuccessListener(doc -> {
                    if (doc.exists()) {
                        Ejercicio ejercicio = doc.toObject(Ejercicio.class);
                        ejercicio.setIdEjercicio(doc.getId());
                        listener.onSuccess(ejercicio);
                    } else {
                        listener.onFailure(new Exception("Ejercicio no encontrado"));
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }

    // OBTENER TODO
    @Override
    public void obtenerTodo(OnTodosObtenidos<Ejercicio> listener) {
        db.collection("ejercicios")
                .get()
                .addOnSuccessListener(query -> {
                    List<Ejercicio> lista = query.toObjects(Ejercicio.class);

                    // Asignar ID de Firestore
                    for (int i = 0; i < lista.size(); i++) {
                        lista.get(i).setIdEjercicio(query.getDocuments().get(i).getId());
                    }

                    listener.onSuccess(lista);
                })
                .addOnFailureListener(listener::onFailure);
    }

    // ACTUALIZAR
    @Override
    public void actualizar(String id, Ejercicio ejercicio, OnActualizado listener) {
        db.collection("ejercicios")
                .document(id)
                .set(ejercicio)
                .addOnSuccessListener(unused -> listener.onSuccess())
                .addOnFailureListener(listener::onFailure);
    }

    // ELIMINAR
    @Override
    public void eliminar(String id, OnEliminado listener) {
        db.collection("ejercicios")
                .document(id)
                .delete()
                .addOnSuccessListener(unused -> listener.onSuccess())
                .addOnFailureListener(listener::onFailure);
    }
}
